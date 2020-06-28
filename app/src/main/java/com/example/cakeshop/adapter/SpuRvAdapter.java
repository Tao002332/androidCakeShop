package com.example.cakeshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.pojo.ProductSPU;
import com.example.cakeshop.utils.AnotherTask;
import com.example.cakeshop.utils.ImgDownload;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */

/**
 *  商品SPU recyclerView的 适配器
 */
public class SpuRvAdapter extends RecyclerView.Adapter {

    private List<ProductSPU> list;
    private Context context;
    private LayoutInflater layoutInflater;

    private static final int VIEW_ONE =1;
    private static final int VIEW_TWO =2;


    public SpuRvAdapter(List<ProductSPU> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }


    @Override
    public int getItemViewType(int position) {
        if (list.size()%2==0) {
            return VIEW_TWO;
        } else  {
            return VIEW_ONE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder=null;
        switch (viewType) {
            case VIEW_ONE:
                holder = new MyViewHodler(layoutInflater.inflate(R.layout.spu_item,parent,false));
                break;
            case VIEW_TWO:
                holder = new MyViewHodlerTwo(layoutInflater.inflate(R.layout.spu_item_two,parent,false));
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductSPU spu = list.get(position);
        String url=spu.getImg();
        switch (getItemViewType(position)) {
            case VIEW_ONE:
                MyViewHodler holder1= (MyViewHodler) holder;
                new AnotherTask(holder1.iv_img).execute(url);
                holder1.tv_title.setText(spu.getTitle());
                holder1.tv_price.setText("最低价"+spu.getPrice());
                if (spu.getDiscount()!=1.00) {
                    holder1.tv_discount.setText("折扣中");
                    holder1.tv_discount.setTextColor(Color.RED);
                } else {
                    holder1.tv_discount.setText("无折扣");
                }
                holder1.tv_pv.setText(spu.getPv()+"人");
                break;
            case VIEW_TWO:
                new AnotherTask(((MyViewHodlerTwo) holder).iv_img).execute(url);
                ((MyViewHodlerTwo) holder).tv_title.setText(spu.getTitle());
                break;
        }



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_title;
        TextView tv_price;
        TextView tv_discount;
        TextView tv_pv;
        View view;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_discount=itemView.findViewById(R.id.tv_discount);
            tv_pv=itemView.findViewById(R.id.tv_pv);
            view=itemView;
        }
    }


    public class MyViewHodlerTwo extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_title;

        public MyViewHodlerTwo(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img);
            tv_title=itemView.findViewById(R.id.tv_title);
        }
    }

}

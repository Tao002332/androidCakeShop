package com.example.cakeshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.pojo.Cart;
import com.example.cakeshop.pojo.ProductSPU;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */

/**
 *  商品SPU recyclerView的 适配器
 */
public class ProductSPURvAdapter extends RecyclerView.Adapter {

    private List<ProductSPU> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public ProductSPURvAdapter(List<ProductSPU> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.spu_item,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ProductSPU spu = list.get(position);
        MyViewHodler hodler1= (MyViewHodler) holder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHodler extends RecyclerView.ViewHolder {

        ImageView iv_img;
        TextView tv_title;
        TextView tv_price;
        TextView tv_pv;
        View view;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_price=itemView.findViewById(R.id.tv_price);
            tv_pv=itemView.findViewById(R.id.tv_pv);
            view=itemView;
        }
    }

}

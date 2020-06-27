package com.example.cakeshop.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.cakeshop.R;
import com.example.cakeshop.pojo.OrderDetail;
import com.example.cakeshop.utils.ImgDownload;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 订单详情列表RecyclerView 的适配器
 */
public class OrderDetailRvAdapter extends RecyclerView.Adapter {

    private List<OrderDetail> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageView holderImg;

    public List<OrderDetail> getList() {
        return list;
    }

    public OrderDetailRvAdapter(List<OrderDetail> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.order_detail_item,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        OrderDetail od=list.get(position);
        MyViewHodler holder1=(MyViewHodler)holder;
        String imgPath= od.getProduct_img();
        holderImg=holder1.iv_img;
        new AnotherTask().execute(imgPath);
        holder1.tv_title.setText(od.getProduct_title());
        holder1.tv_num.setText(od.getNum()+"");
        holder1.tv_price.setText(od.getPrice()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHodler extends ViewHolder {
        TextView tv_title;
        ImageView iv_img;
        TextView tv_num;
        TextView  tv_price;
        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            iv_img=itemView.findViewById(R.id.iv_img);
            tv_title=itemView.findViewById(R.id.tv_title);
            tv_num=itemView.findViewById(R.id.tv_num);
            tv_price=itemView.findViewById(R.id.tv_price);
        }
    }


    /**
     * 异步任务
     */
    private class AnotherTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            return ImgDownload.getBitmapByUrl(strings[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            holderImg.setImageBitmap(bitmap);
        }
    }


}

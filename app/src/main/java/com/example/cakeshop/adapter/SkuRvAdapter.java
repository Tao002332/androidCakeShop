package com.example.cakeshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.pojo.Sku;
import com.google.gson.Gson;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/28
 */
public class SkuRvAdapter extends RecyclerView.Adapter {

    private List<Sku> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public SkuRvAdapter(List<Sku> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.spuinfo_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        Sku sku = list.get(position);
        Gson gson=new Gson();

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv_skuinfo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_skuinfo=itemView.findViewById(R.id.tv_skuinfo);
        }
    }

}

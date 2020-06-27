package com.example.cakeshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.pojo.Cart;
import com.example.cakeshop.pojo.ProductSPU;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */
public class ProductRvAdapter extends RecyclerView.Adapter {

    private List<ProductSPU> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public ProductRvAdapter(List<ProductSPU> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHodler extends RecyclerView.ViewHolder {

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
        }
    }

}

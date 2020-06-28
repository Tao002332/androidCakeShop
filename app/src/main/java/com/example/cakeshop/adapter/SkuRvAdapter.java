package com.example.cakeshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.pojo.Attribute;
import com.example.cakeshop.pojo.Sku;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * created by guigui
 * on 2020/6/28
 */
public class SkuRvAdapter extends RecyclerView.Adapter {

    private List<Sku> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback callback;
    private List<Boolean> isChecked;

    public SkuRvAdapter(List<Sku> list, Context context,Callback callback) {
        this.list = list;
        this.context = context;
        this.callback=callback;
        this.layoutInflater=LayoutInflater.from(context);
        this.isChecked=new LinkedList<>();
        initIsChecked();
    }

    /**
     * 设置全部未选
     */
    private void initIsChecked(){
        for (int i=0;i<list.size();i++ ) {
            isChecked.add(i,false);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.spuinfo_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        final Sku sku = list.get(position);
        Gson gson=new Gson();
        Attribute attribute = gson.fromJson(sku.getAttribute_list(), Attribute.class);
        String skuInfo="尺寸"+attribute.getSizeTitle()+
                "| 高度"+attribute.getHeightTitle()+
                "| 单价"+ sku.getPrice() +"每/个" +
                "| 库存"+sku.getStock();
        holder1.tv_skuinfo.setText(skuInfo);
        if (isChecked.get(position)) {
            holder1.tv_skuinfo.setBackground(context.getDrawable(R.drawable.selected_border));
        } else {
            holder1.tv_skuinfo.setBackground(null);
        }
        holder1.tv_skuinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.getStockAndId(sku.getId(),sku.getStock(),sku.getPrice());
                initIsChecked();
                isChecked.set(position,true);
                notifyDataSetChanged();
            }
        });
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

    public interface  Callback {
        void getStockAndId(Integer id, Integer stock,Double price);
    }

}

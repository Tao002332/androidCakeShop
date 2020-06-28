package com.example.cakeshop.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.activity.OrderActivity;
import com.example.cakeshop.activity.PreInfoActivity;
import com.example.cakeshop.activity.PreInfoManagerActivity;
import com.example.cakeshop.pojo.PreInfo;
import com.example.cakeshop.resultType.ResultActivityCode;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 预留信息列表RecyclerView 的适配器
 */
public class PreInfoRvAdapter extends RecyclerView.Adapter {

    private List<PreInfo> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public PreInfoRvAdapter(List<PreInfo> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.preinfo_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final PreInfo preInfo = list.get(position);
        final MyViewHolder holder1 = (MyViewHolder) holder;
        holder1.tv_name.setText(preInfo.getNickname());
        holder1.tv_phone.setText(preInfo.getPhone());
        holder1.tv_address.setText(preInfo.getAddress());
        holder1.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PreInfoManagerActivity.class);
                intent.putExtra("actionSave",false);
                intent.putExtra("preInfo",preInfo);
                ((PreInfoActivity)context).startActivityForResult(intent,ResultActivityCode.TO_NEXT_ACTIVITY);
            }
        });
        // 选择 一条预留信息
        holder1.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderActivity.class);
                intent.putExtra("preInfo",preInfo);
                ((PreInfoActivity)context).setResult(ResultActivityCode.SELECT_ROLLBACK,intent);
                ((PreInfoActivity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        TextView tv_edit;
        View view;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name=itemView.findViewById(R.id.tv_name);
            tv_phone=itemView.findViewById(R.id.tv_phone);
            tv_address=itemView.findViewById(R.id.tv_address);
            tv_edit=itemView.findViewById(R.id.tv_edit);
            view=itemView;
        }
    }

}

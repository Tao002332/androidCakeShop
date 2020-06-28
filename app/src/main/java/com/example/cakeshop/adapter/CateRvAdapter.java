package com.example.cakeshop.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.pojo.Cate;

import java.util.LinkedList;
import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */

/**
 * 分类 适配器
 */
public class CateRvAdapter extends RecyclerView.Adapter {

    private List<Cate> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private Callback callback;
    private List<Boolean> isChecked;

    public CateRvAdapter(List<Cate> list, Context context,Callback callback) {
        this.list = list;
        this.context = context;
        this.callback=callback;
        this.layoutInflater=LayoutInflater.from(context);
        isChecked=new LinkedList<>();
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
        View view=layoutInflater.inflate(R.layout.cate_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Cate cate = list.get(position);
        MyViewHolder holder1= (MyViewHolder) holder;
        holder1.tv_title.setText(cate.getTitle());
        if (isChecked.get(position)) {
            holder1.tv_title.setBackground(context.getDrawable(R.drawable.selected_border));
        } else {
            holder1.tv_title.setBackground(null);
        }
        holder1.tv_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.updateContentData(cate.getId());
                initIsChecked();
                isChecked.set(position,true);
                notifyDataSetChanged();
            }
        });
        if(cate.getData_flag()!=1) {
            holder1.tv_title.setTextColor(Color.GRAY);
            holder1.tv_title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"该分类已下架",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
        }
    }

    public interface  Callback {
        void updateContentData(Integer id);
    }

}

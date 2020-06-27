package com.example.cakeshop.adapter;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.activity.UserOrderDetailActivity;
import com.example.cakeshop.pojo.Order;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */
public class UserOrderRvAdapter  extends RecyclerView.Adapter{

    private List<Order> list;
    private Context context;
    private LayoutInflater layoutInflater;


    public UserOrderRvAdapter(List<Order> list, Context context) {
        this.list = list;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.order_item,parent,false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Order order = list.get(position);
        MyViewHodler holder1 = (MyViewHodler) holder;
        holder1.tv_order_no.setText(order.getOrder_no());
        holder1.tv_product_money.setText(order.getProduct_money()+"");
        holder1.tv_created_at.setText(order.getCreated_at());
        holder1.view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String msg="订单号:"+order.getOrder_no()+"\n " +
                        "购买总价:"+order.getProduct_money()+"\n " +
                        "订单创建时间:"+order.getCreated_at()+"\n" +
                        "收货方式为："+(order.getDeliver_type()==0?"送货上门":"自提")+"\n " +
                        "预留手机号:"+order.getRecevicer_phone()+"\n " +
                        "地址："+order.getRecevicer_address();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(msg);
                builder.setTitle("订单显示");
                builder.setPositiveButton("保存订单号到剪切板", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clipData=ClipData.newPlainText(null,order.getOrder_no());
                        clipboard.setPrimaryClip(clipData);
                        Toast.makeText(context,"已保存"+order.getOrder_no(),Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("关闭",null);
                builder.show();
                return true;
            }
        });
        holder1.tv_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UserOrderDetailActivity.class);
                intent.putExtra("orderId",order.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHodler extends RecyclerView.ViewHolder {
        TextView tv_order_no;
        TextView tv_product_money;
        TextView tv_created_at;
        TextView tv_show;
        View view;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            tv_order_no=itemView.findViewById(R.id.tv_order_no);
            tv_product_money=itemView.findViewById(R.id.tv_product_money);
            tv_created_at=itemView.findViewById(R.id.tv_created_at);
            tv_show=itemView.findViewById(R.id.tv_show);
            view=itemView;
        }
    }


}

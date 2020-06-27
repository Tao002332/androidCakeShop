package com.example.cakeshop.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.dao.CartDao;
import com.example.cakeshop.pojo.Cart;
import com.example.cakeshop.utils.ImgDownload;

import java.util.LinkedList;
import java.util.List;
/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 商品列表RecyclerView 的适配器
 */
public class CartRvAdapter extends RecyclerView.Adapter {

    private List<Cart> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private ImageView holderImg;
    private List<Cart> checkedList;
    private Callback callback;
    private CartDao cartDao;

    public List<Cart> getList() {
        return list;
    }

    public List<Cart> getCheckedList() {
        return checkedList;
    }

    public void clearCheckedList() {
        checkedList.clear();
    }


    public CartRvAdapter(List<Cart> list,CartDao cartDao, Context context,Callback callback) {
        this.list = list;
        this.checkedList=new LinkedList<>();
        this.context = context;
        this.cartDao= cartDao;
        this.callback=callback;
        this.layoutInflater=LayoutInflater.from(context);
    }

    /**
     * 初始化 recycler view
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.cart_item,parent,false);
        return new MyViewHodler(view);
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Cart cart = list.get(position);
        final MyViewHodler holder1 = (MyViewHodler) holder;
        String imgPath = cart.getProduct_img();
        holderImg=holder1.iv_img;
        new AnotherTask().execute(imgPath);
        holder1.tv_title.setText(cart.getProduct_title());
        holder1.tv_num.setText(cart.getNum()+"");
        holder1.tv_price.setText(cart.getPrice()+"");
        holder1.tv_origin_price.setText("");
        holder1.cb_item.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(holder1.cb_item.isChecked()) {
                    cart.setChecked(true);
                    checkedList.add(cart);
                } else {
                    cart.setChecked(false);
                    checkedList.remove(cart);
                }
                cartDao.update(cart);
                callback.updateTotalAll(countTotal());
            }
        });
        holder1.cb_item.setChecked(cart.getChecked());
        /* 判断是否有折扣 有折扣设置原价删除线*/
        if(cart.getDiscount()!= 1.00) {
            holder1.tv_origin_price.setText(cart.getOrigin_price()+"");
            holder1.tv_origin_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
            holder1.tv_price.setTextColor(Color.RED);
        }
        // 绑定增加数量事件
        holder1.iv_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cart.setNum(cart.getNum()+1);
                cartDao.update(cart);
                notifyDataSetChanged();
            }
        });
        // 绑定减少数量事件
        holder1.iv_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cart.getNum()!=1) {
                    cart.setNum(cart.getNum()-1);
                    cartDao.update(cart);
                    notifyDataSetChanged();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("是否要删除该商品");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            cartDao.delete(cart);
                            list.remove(cart);
                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("取消",null);
                    builder.show();
                }

            }
        });
    }

    /**
     * 计算价格
     * @return
     */
    private String countTotal() {
        double sum=0;
        for (int i=0;i<checkedList.size();i++) {
            sum += checkedList.get(i).getNum() * checkedList.get(i).getPrice();
        }
        return "合计：￥"+sum;
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    /**
     * 视图占位
     */
    public class MyViewHodler extends RecyclerView.ViewHolder {
        TextView tv_title;
        ImageView iv_img;
        ImageView iv_up;
        ImageView iv_down;
        TextView tv_num;
        TextView tv_price;
        TextView tv_origin_price;
        CheckBox cb_item;

        public MyViewHodler(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_origin_price = itemView.findViewById(R.id.tv_origin_price);
            iv_img = itemView.findViewById(R.id.iv_img);
            iv_up = itemView.findViewById(R.id.iv_up);
            iv_down = itemView.findViewById(R.id.iv_down);
            cb_item = itemView.findViewById(R.id.cb_item);
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

    /**
     * 回调接口
     */
    public interface Callback{
        /**
         * 更新 总数
         */
        void updateTotalAll(String value);
    }


}

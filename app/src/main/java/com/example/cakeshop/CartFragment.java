package com.example.cakeshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.activity.OrderActivity;
import com.example.cakeshop.adapter.CartRvAdapter;
import com.example.cakeshop.dao.CartDao;
import com.example.cakeshop.pojo.Cart;
import com.example.cakeshop.resultType.ResultActivityCode;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/24
 */
/**
 * 购物车 fragment
 */
public class CartFragment extends Fragment {

    private  TextView tv_manger;
    private  TextView tv_cancel;
    private Button btn_to_order;
    private Button btn_delete;
    private  CheckBox cb_all;
    private  TextView tv_total;
    private LinearLayout is_empty;
    private RecyclerView rlv;
    private List<Cart> list;
    private CartRvAdapter cartRvAdapter;
    private CartDao cartDao;


    /**
     * 填充购物车layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = View.inflate(getActivity(), R.layout.cart_fragment, null);
        initUI(view1);
        return view1;
    }

    /**
     *  初始化ui
     * @param view1
     */
    private void initUI(View view1) {
        cartDao=new CartDao(getContext());
        tv_manger =view1.findViewById(R.id.tv_manger);
        tv_cancel =view1.findViewById(R.id.tv_cancel);
        rlv =view1.findViewById(R.id.rlv);
        is_empty =view1.findViewById(R.id.is_empty);
        cb_all =view1.findViewById(R.id.cb_all);
        tv_total =view1.findViewById(R.id.tv_total);
        btn_to_order =view1.findViewById(R.id.btn_to_order);
        btn_delete =view1.findViewById(R.id.btn_delete);
        fetchData();
        tv_manger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_delete.setVisibility(View.VISIBLE);
                tv_cancel.setVisibility(View.VISIBLE);
                btn_to_order.setVisibility(View.GONE);
                tv_manger.setVisibility(View.GONE);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn_delete.setVisibility(View.GONE);
                tv_cancel.setVisibility(View.GONE);
                btn_to_order.setVisibility(View.VISIBLE);
                tv_manger.setVisibility(View.VISIBLE);
            }
        });
        // 为空显示 为空的布局
        if (list.size()==0) {
            is_empty.setVisibility(View.VISIBLE);
            rlv.setVisibility(View.GONE);
        } else  {
            is_empty.setVisibility(View.GONE);
            rlv.setVisibility(View.VISIBLE);
            rlv.setLayoutManager(new LinearLayoutManager(getContext()));
            cartRvAdapter = new CartRvAdapter(list,cartDao, getContext(), new CartRvAdapter.Callback() {
                @Override
                public void updateTotalAll(String value) {
                    tv_total.setText(value);
                }
            });
            rlv.setAdapter(cartRvAdapter);
        }
        // 初始化全选
        defaultCheckAll();
        cb_all.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                for (int i=0;i<list.size();i++) {
                    cartRvAdapter.getList().get(i).setChecked(cb_all.isChecked());
                    cartDao.update(cartRvAdapter.getList().get(i));
                    cartRvAdapter.notifyDataSetChanged();
                }
            }
        });
        // 删除选择
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartRvAdapter.getList().removeAll(cartRvAdapter.getCheckedList());
                cartDao.deleteList(cartRvAdapter.getCheckedList());
                cartRvAdapter.clearCheckedList();
                cartRvAdapter.notifyDataSetChanged();
                if(cartRvAdapter.getList().size()==0) {
                    is_empty.setVisibility(View.VISIBLE);
                    rlv.setVisibility(View.GONE);
                    tv_total.setText("合计：￥0");
                }
            }
        });
        // 跳转 支付界面
        btn_to_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartRvAdapter.getCheckedList().size()>=1) {
                    Intent intent = new Intent(getContext(),OrderActivity.class);
                    startActivityForResult(intent, ResultActivityCode.TO_NEXT_ACTIVITY);
                } else {
                    Toast.makeText(getContext(),"至少要选择一个商品",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 判断 是否全选
     */
    private  void defaultCheckAll() {
        boolean flag= true;
        for (int i=0;i<list.size();i++) {
            if(cartRvAdapter.getList().get(i).getChecked() != flag) {
                cb_all.setChecked(false);
            }
        }
    }


    /**
     * 获取数据
     */
    private void fetchData() {
//        saveList();
        list=cartDao.findAll();
    }


//    private  void saveList() {
//        Cart cart1 = new Cart(6, 10, 12.00, "蛋糕", "http://newsblogsite.oss-cn-beijing.aliyuncs.com/%E6%A8%B1%E6%A1%83%E6%9C%A8-05.JPG", 3, 13.00, 0.95, true);
//        Cart cart2 = new Cart(7, 20, 13.00, "蛋糕特选", "http://newsblogsite.oss-cn-beijing.aliyuncs.com/%E6%A8%B1%E6%A1%83%E6%9C%A8-05.JPG", 3, 13.00, 1.00, false);
//        cartDao.save(cart1);
//        cartDao.save(cart2);
//    }


}

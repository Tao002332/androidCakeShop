package com.example.cakeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.adapter.OrderDetailRvAdapter;
import com.example.cakeshop.api.OrderApi;
import com.example.cakeshop.pojo.Order;
import com.example.cakeshop.pojo.OrderDetail;
import com.example.cakeshop.pojo.result.ResultOrderAndDetail;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/24
 */
/**
 * 订单查询fragment
 */
public class OrderSearchFragment extends Fragment {

    private EditText et_order_no;
    private EditText et_phone;
    private  RecyclerView rlv;
    private  TextView tv_info;
    private OrderDetailRvAdapter rvAdapter;





    /**
     * 填充订单查询 layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view1 = View.inflate(getActivity(), R.layout.order_search_fragment, null);
        et_order_no= view1.findViewById(R.id.et_order_no);
        et_phone= view1.findViewById(R.id.et_phone);
        rlv = view1.findViewById(R.id.rlv);
        Button bt_search =view1.findViewById(R.id.btn_search);
        tv_info= view1.findViewById(R.id.tv_info);
        bt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderNo= et_order_no.getText().toString().trim();
                String phone= et_phone.getText().toString().trim();
                /**
                 * 通过 api 查询 得到返回值
                 */
                OrderApi.findOrderByOrderNoAndPhone(new Order(orderNo,phone), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String jsonData = response.body().string();
                        parseJSONWithOrder(jsonData);
                    }
                });
            }
        });

        return view1;
    }

    private void binderData(Order order,List<OrderDetail> list) {
        String showText="下单时间为："+order.getCreated_at()+"\n" +
                "订单状态为:"+order.getOrder_status() + "\n" +
                "收货方式为："+(order.getDeliver_type()==0?"送货上门":"自提")+"\n" +
                "地址："+order.getRecevicer_address();
        tv_info.setText(showText);
        rvAdapter=new OrderDetailRvAdapter(list,getContext());
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv.setAdapter(rvAdapter);
    }


    /**
     * 解析j'son 转换为 resultOrder 类
     * @param jsonData
     */
    private void parseJSONWithOrder(String jsonData) {
        Gson gson = new Gson();
        final ResultOrderAndDetail resultOrderAndDetail = gson.fromJson(jsonData, ResultOrderAndDetail.class);
        if (resultOrderAndDetail.isFlag()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binderData(resultOrderAndDetail.getData().getOrder(),resultOrderAndDetail.getData().getOrderDetails());
                }
            });
        }
    }

//    private void fetchData() {
//        list=new LinkedList<>();
//        list.add(new OrderDetail(1,1,12.00,"蓝莓特选蛋糕-54","http://newsblogsite.oss-cn-beijing.aliyuncs.com/%E6%A8%B1%E6%A1%83%E6%9C%A8-05.JPG"));
//        list.add(new OrderDetail(2,1,15.00,"蓝莓特选蛋糕-29","https://paimgcdn.baidu.com/43562DF2FBF40E04?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F3267062144.jpg&rz=urar_2_968_600&v=0"));
//    }


}

package com.example.cakeshop.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.adapter.OrderDetailRvAdapter;
import com.example.cakeshop.api.OrderApi;
import com.example.cakeshop.pojo.OrderDetail;
import com.example.cakeshop.pojo.result.ResultOrderAndDetail;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/27
 */
public class UserOrderDetailActivity extends Activity {

    private OrderDetailRvAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order_detail);
        fetchData();
    }

    private void fetchData() {
        int orderId = getIntent().getIntExtra("orderId", -1);
        OrderApi.findByid(orderId, new Callback() {
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


    /**
     * 解析j'son 转换为 resultOrder 类
     * @param jsonData
     */
    private void parseJSONWithOrder(String jsonData) {
        Gson gson = new Gson();
        final ResultOrderAndDetail resultOrderAndDetail = gson.fromJson(jsonData, ResultOrderAndDetail.class);
        if (resultOrderAndDetail.isFlag()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initUI(resultOrderAndDetail.getData().getOrderDetails());
                }
            });
        }
    }


    private void initUI(List<OrderDetail> list) {
        adapter=new OrderDetailRvAdapter(list,this);
        RecyclerView rlv = findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(adapter);
    }


    public void rollback(View view) {
        finish();
    }
}

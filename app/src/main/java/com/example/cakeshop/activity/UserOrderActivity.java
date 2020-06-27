package com.example.cakeshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.MainActivity;
import com.example.cakeshop.R;
import com.example.cakeshop.adapter.UserOrderRvAdapter;
import com.example.cakeshop.api.OrderApi;
import com.example.cakeshop.pojo.Order;
import com.example.cakeshop.pojo.result.ResultOrder;
import com.example.cakeshop.pojo.result.ResultOrderAndDetail;
import com.example.cakeshop.resultType.ResultFragmentCode;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/27
 */
public class UserOrderActivity extends Activity {


    private UserOrderRvAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_order);
        fetchData();
    }


    private void fetchData() {
        OrderApi.findByUid(new Callback() {
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
     * 解析json 转为 resultOrder
     * @param jsonData
     */
    private void parseJSONWithOrder(String jsonData) {
        Gson gson = new Gson();
        final ResultOrder resultOrder = gson.fromJson(jsonData, ResultOrder.class);
        if (resultOrder.isFlag()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initUI(resultOrder.getData());
                }
            });
        }
    }


    private void initUI(List<Order> list) {
        adapter=new UserOrderRvAdapter(list,this);
        RecyclerView rlv=findViewById(R.id.rlv);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rlv.setAdapter(adapter);
    }



    public void rollback(View view) {
        this.finish();
    }
}

package com.example.cakeshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.R;
import com.example.cakeshop.adapter.PreInfoRvAdapter;
import com.example.cakeshop.api.PreInfoApi;
import com.example.cakeshop.pojo.PreInfo;
import com.example.cakeshop.pojo.result.ResultPreInfo;
import com.example.cakeshop.resultType.ResultActivityCode;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 预留信息的activity
 */
public class PreInfoActivity extends Activity {

    private RecyclerView rlv;
    private PreInfoRvAdapter rvAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preinfo);
        initUi();
    }

    protected  void initUi() {
        fetchData();
        rlv=findViewById(R.id.rlv);
    }

    /**
     * 绑定
     * @param list
     */
    private void binderListData(List<PreInfo> list) {
        rlv.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter=new PreInfoRvAdapter(list,this);
        rlv.setAdapter(rvAdapter);
    }


    protected  void fetchData() {
        PreInfoApi.findByUid(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData= response.body().string();
                parseJSONWithPreInfo(jsonData);
            }
        });
    }

    /**
     * 解析json 获取preinfo 集合 并设置状态
     * @param jsonData
     */
    private void parseJSONWithPreInfo(String jsonData) {
        Gson gson = new Gson();
        System.out.println(jsonData);
        final ResultPreInfo resultPreInfo = gson.fromJson(jsonData, ResultPreInfo.class);
        if(resultPreInfo.isFlag()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binderListData(resultPreInfo.getData());
                }
            });
        }
    }


    /**
     *  回退 关闭该界面
     * @param view
     */
    public void rollback(View view) {
        this.finish();
    }

    /**
     * 跳转 预留信息管理
     * @param view
     */
    public void toPreInfoManager(View view) {
        Intent intent = new Intent(this, PreInfoManagerActivity.class);
        intent.putExtra("actionSave",true);
        startActivityForResult(intent,ResultActivityCode.TO_NEXT_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ResultActivityCode.UPDATE_ACTIVITY:
                refresh();
                break;
            default: break;
        }
    }

    /**
     * 刷新activity
     */
    private void refresh() {
        onCreate(null);
    }
}

package com.example.cakeshop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.cakeshop.MainActivity;
import com.example.cakeshop.R;
import com.example.cakeshop.api.UserApi;
import com.example.cakeshop.pojo.result.Result;
import com.example.cakeshop.resultType.ResultActivityCode;
import com.example.cakeshop.resultType.ResultFragmentCode;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 设置的activity
 */
public class SettingActivity  extends Activity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }

    /**
     * 回退 用户 fragment
     * @param view
     */
    public void rollback(View view) {
        returnUserFragment();
    }

    /**
     * 退出登录 返回 用户fragment
     * @param view
     */
    public void logout(View view) {
        UserApi.logout(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                parseJSONWithResult(jsonData);
            }
        });
    }


    /**
     * 解析json 获取result 并清空sp
     * @param jsonData
     */
    private void parseJSONWithResult(String jsonData){
        Gson gson = new Gson();
        Result result = gson.fromJson(jsonData, Result.class);
        if (result.isFlag()) {
            SharedPreferences.Editor edit = sp.edit();
            edit.clear();
            edit.commit();
            returnUserFragment();
        }
    }

    /**
     * 返回 用户fragment
     */
    private void returnUserFragment() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(ResultFragmentCode.USER, intent);
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ResultActivityCode.TO_NEXT_ACTIVITY: returnUserFragment();break;
            default:break;
        }
    }

    /**
     * 修改个人信息
     * @param view
     */
    public void toEditUserInfo(View view) {
        Intent intent = new Intent(this, EditUserActivity.class);
        startActivityForResult(intent, ResultActivityCode.TO_NEXT_ACTIVITY);
    }
}

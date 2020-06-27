package com.example.cakeshop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cakeshop.MainActivity;
import com.example.cakeshop.R;
import com.example.cakeshop.api.UserApi;
import com.example.cakeshop.pojo.User;
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
 * on 2020/6/26
 */

/**
 * 编辑用户
 */
public class EditUserActivity   extends Activity  {

    private EditText et_name;
    private TextView tv_email;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        initUI();
    }

    private void initUI() {
        et_name=findViewById(R.id.et_name);
        tv_email=findViewById(R.id.tv_email);
        fetchData();
    }


    private void fetchData() {
        sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        String nickname = sp.getString("nickname","");
        String email = sp.getString("email","");
        et_name.setText(nickname);
        tv_email.setText(email);
    }


    public void rollback(View view) {
        this.finish();
    }

    public void editUserInfo(View view) {
        String nickname= et_name.getText().toString().trim();
        if(TextUtils.isEmpty(nickname)) {
            Toast.makeText(this,"昵称不能为空",Toast.LENGTH_LONG).show();
        } else {
            final User user = new User();
            user.setNickname(nickname);
            UserApi.editUserInfo(user, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String jsonData = response.body().string();
                    parseJSONWithResult(jsonData,user);
                }
            });
        }
    }

    /**
     * 解析json 获取result 并清空sp
     * @param jsonData
     */
    private void parseJSONWithResult(String jsonData,User user){
        Gson gson = new Gson();
        Result result = gson.fromJson(jsonData, Result.class);
        if (result.isFlag()) {
            SharedPreferences.Editor edit = sp.edit();
            edit.putString("nickname",user.getNickname());
            edit.commit();
            Intent intent = new Intent(this, SettingActivity.class);
            setResult(ResultActivityCode.TO_NEXT_ACTIVITY, intent);
            this.finish();
        }
    }

}

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

import com.example.cakeshop.R;
import com.example.cakeshop.api.UserApi;
import com.example.cakeshop.pojo.User;
import com.example.cakeshop.pojo.result.Result;
import com.example.cakeshop.resultType.ResultActivityCode;
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
public class EditUserPasswordActivity extends Activity  {

    private EditText et_password;
    private EditText et_check_password;
    private SharedPreferences sp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_password);
        initUI();
    }

    private void initUI() {
        et_password=findViewById(R.id.et_password);
        et_check_password=findViewById(R.id.et_check_password);
        sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
    }


    public void rollback(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        setResult(ResultActivityCode.ROLLBACK, intent);
        this.finish();
    }

    public void changePassword(View view) {
        String password= et_password.getText().toString().trim();
        String checkPassword= et_check_password.getText().toString().trim();
        if (validate(password,checkPassword)) {
            final User user = new User();
            user.setPassword(password);
            UserApi.userChangePassword(user, new Callback() {
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
     * 验证 密码 确认密码
     * @param password
     * @param checkPassword
     * @return
     */
    private Boolean validate( String password, String checkPassword) {
        if(TextUtils.isEmpty(password)) {
            Toast.makeText(this,"密码不能为空",Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(TextUtils.isEmpty(checkPassword)) {
            Toast.makeText(this,"确认密码不能为空",Toast.LENGTH_SHORT).show();
            return  false;
        }
        if(!password.equals(checkPassword) ) {
            Toast.makeText(this,"确认密码不一致",Toast.LENGTH_SHORT).show();
            return  false;
        }
        return true;
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
            edit.clear();
            edit.commit();
            Intent intent = new Intent(this, SettingActivity.class);
            setResult(ResultActivityCode.TO_NEXT_ACTIVITY, intent);
            this.finish();
        }
    }

}

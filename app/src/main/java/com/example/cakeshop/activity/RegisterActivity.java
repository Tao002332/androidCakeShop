package com.example.cakeshop.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cakeshop.MainActivity;
import com.example.cakeshop.R;
import com.example.cakeshop.api.UserApi;
import com.example.cakeshop.pojo.User;
import com.example.cakeshop.pojo.result.Result;
import com.example.cakeshop.resultType.ResultFragmentCode;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/28
 */
public class RegisterActivity extends Activity {

    private EditText et_user_name;
    private EditText et_password;
    private EditText et_check_password;
    private EditText et_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
    }

    private void initUI() {
        et_user_name=findViewById(R.id.et_user_name);
        et_password=findViewById(R.id.et_password);
        et_check_password=findViewById(R.id.et_check_password);
        et_email=findViewById(R.id.et_email);
    }



    public void rollback(View view) {
        returnUserFragment();
    }
    /**
     * 返回 用户fragment
     */
    private void returnUserFragment() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(ResultFragmentCode.USER, intent);
        this.finish();
    }

    public void toRegister(View view) {
        String userName = et_user_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String checkPassword = et_check_password.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        if (validate(userName, password, checkPassword,email)) {
            User user = new User();
            user.setUser_name(userName);
            user.setPassword(password);
            user.setEmail(email);
            UserApi.register(user, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"已发送邮箱 请激活",Toast.LENGTH_LONG).show();
                        }
                    });
                    returnUserFragment();
                }
            });
        }
    }


    /**
     * 验证 姓名  密码 确认密码 邮箱
     * @param userName
     * @param password
     * @param checkPassword
     * @param email
     * @return
     */
    private Boolean validate(String userName, String password, String checkPassword,String email) {
        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(this,"用户名不能为空",Toast.LENGTH_SHORT).show();
            return  false;
        }
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
        return isEmail(email);
    }


    public boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        if (TextUtils.isEmpty(strPattern)) {
            return false;
        } else {
            return strEmail.matches(strPattern);
        }
    }

}

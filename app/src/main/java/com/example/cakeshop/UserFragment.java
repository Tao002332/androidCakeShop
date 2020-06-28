package com.example.cakeshop;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cakeshop.activity.PreInfoActivity;
import com.example.cakeshop.activity.RegisterActivity;
import com.example.cakeshop.activity.SettingActivity;
import com.example.cakeshop.activity.UserOrderActivity;
import com.example.cakeshop.api.UserApi;
import com.example.cakeshop.pojo.result.ResultToken;
import com.example.cakeshop.pojo.User;
import com.example.cakeshop.pojo.result.ResultUser;
import com.example.cakeshop.resultType.ResultActivityCode;
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
 * 用户 fragment
 */
public class UserFragment extends Fragment {

    private  ImageView iv_show_pwd;
    private  ImageView iv_not_show_pwd ;
    private ImageView iv_setting;
    private  EditText et_login_name ;
    private  EditText et_password ;
    private TextView tv_nickname ;
    private RelativeLayout is_login;
    private LinearLayout is_not_login;
    private LinearLayout is_login_name;
    private LinearLayout to_preinfo;
    private LinearLayout to_order_search;
    private SharedPreferences.Editor edit;
    private SharedPreferences sp;

    /**
     * 填充用户 layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.user_fragment, null);
        initUi(view);
        return view;
    }

    protected  void initUi(View view ) {
        iv_setting =view.findViewById(R.id.iv_setting);
        iv_show_pwd =view.findViewById(R.id.iv_show_pwd);
        iv_not_show_pwd =view.findViewById(R.id.iv_not_show_pwd);
        et_login_name = view.findViewById(R.id.et_login_name);
        et_password = view.findViewById(R.id.et_password);
        is_login=view.findViewById(R.id.is_login);
        is_not_login=view.findViewById(R.id.is_not_login);
        is_login_name=view.findViewById(R.id.is_login_name);
        to_preinfo=view.findViewById(R.id.to_preinfo);
        to_order_search=view.findViewById(R.id.to_order_search);
        tv_nickname = view.findViewById(R.id.tv_nickname);
        Button btn_register=view.findViewById(R.id.btn_register);
        Button btn_login= view.findViewById(R.id.btn_login);
        iv_show_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_not_show_pwd.setVisibility(View.VISIBLE);
                iv_show_pwd.setVisibility(View.GONE);
                et_password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
            }
        });
        iv_not_show_pwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_not_show_pwd.setVisibility(View.GONE);
                iv_show_pwd.setVisibility(View.VISIBLE);
                et_password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        });
        iv_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), SettingActivity.class);
                startActivityForResult(intent,ResultActivityCode.TO_NEXT_ACTIVITY);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toLogin();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getContext(), RegisterActivity.class);
                startActivityForResult(intent,ResultActivityCode.TO_NEXT_ACTIVITY);
            }
        });
        to_preinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toPreinfo();
            }
        });
        to_order_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toOrderSearch();
            }
        });
        sp=getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        boolean isLogin = sp.getBoolean("isLogin", false);
        if(isLogin) {
            logined();
        }
    }

    /**
     * 登录展示界面
     */
    private void logined() {
        is_login.setVisibility(View.VISIBLE);
        is_login_name.setVisibility(View.VISIBLE);
        is_not_login.setVisibility(View.GONE);
        iv_setting.setVisibility(View.VISIBLE);
        tv_nickname.setText(sp.getString("nickname",""));
    }


    /**
     * 查询自己的订单
     */
    private void toOrderSearch() {
        Intent intent = new Intent(getContext(), UserOrderActivity.class);
        startActivity(intent);
    }

    /**
     * 管理 预留信息
     */
    private void toPreinfo() {
        Intent intent = new Intent(getContext(), PreInfoActivity.class);
        startActivity(intent);
    }


    /**
     * 登录
     */
    protected  void toLogin() {
        String user_name=et_login_name.getText().toString().trim();
        String password=et_password.getText().toString().trim();
        if(TextUtils.isEmpty(user_name) || TextUtils.isEmpty(password)) {
            Toast.makeText(getContext(),"账号密码不能为空",Toast.LENGTH_LONG).show();
        } else {
            User user = new User(user_name, password);
            edit=sp.edit();
            /**
             * 调 后台api
             */
            UserApi.login(user, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String jsonData= response.body().string();
                    parseJSONWithToken(jsonData);
                    UserApi.getInfo(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println(e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String jsonData= response.body().string();
                            parseJSONWithUser(jsonData);
                        }
                    });
                }
            });
        }
    }


    /**
     * 解析json 获取token 并存入 sp
     * @param jsonData
     */
    private void parseJSONWithToken(String jsonData){
        Gson gson = new Gson();
        ResultToken resultToken = gson.fromJson(jsonData, ResultToken.class);
        if (resultToken.isFlag()) {
            String token = resultToken.getData().getToken();
            edit.putString("token",token);
            edit.commit();
        }
    }

    /**
     * 解析json 获取user 并设置状态   更新ui仅可子线程调用
     * @param jsonData
     */
    private void parseJSONWithUser(String jsonData) {
        Gson gson = new Gson();
        ResultUser resultUser = gson.fromJson(jsonData, ResultUser.class);
        if(resultUser.isFlag()) {
            edit.putBoolean("isLogin",true);
            edit.putInt("id",resultUser.getData().getId());
            edit.putString("nickname",resultUser.getData().getNickname());
            edit.putString("email",resultUser.getData().getEmail());
            edit.commit();
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    logined();
                }
            });
        }
    }




}

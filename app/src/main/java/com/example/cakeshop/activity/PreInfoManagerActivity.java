package com.example.cakeshop.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cakeshop.R;
import com.example.cakeshop.api.PreInfoApi;
import com.example.cakeshop.pojo.PreInfo;
import com.example.cakeshop.pojo.result.Result;
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
 * 预留信息管理activity
 */
public class PreInfoManagerActivity extends Activity {

    private TextView tv_title;
    private boolean isSave;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_address;
    private PreInfo preInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preinfo_manager);
        initUi();
    }

    protected  void initUi() {
        tv_title=findViewById(R.id.tv_title);
        et_name=findViewById(R.id.et_name);
        et_phone=findViewById(R.id.et_phone);
        et_address=findViewById(R.id.et_address);
        Button  btn_delete= findViewById(R.id.btn_delete);
        isSave = getIntent().getBooleanExtra("actionSave",false);
        String title=  isSave? "添加收货地址":"编辑收货地址";
        tv_title.setText(title);
         preInfo = (PreInfo) getIntent().getSerializableExtra("preInfo");
         String sname=isSave?"":preInfo.getNickname();
         String sphone=isSave?"":preInfo.getPhone();
         String saddress=isSave?"":preInfo.getAddress();
         btn_delete.setVisibility(isSave?View.GONE:View.VISIBLE);
         et_name.setText(sname);
         et_phone.setText(sphone);
         et_address.setText(saddress);
    }

    /**
     * 无需传参  直接finish 当前activity
     * @param view
     */
    public void rollback(View view) {
        this.finish();
    }

    public void savePreInfo(View view) {
        String sname=et_name.getText().toString().trim();
        String sphone=et_phone.getText().toString().trim();
        String saddress=et_address.getText().toString().trim();
        /**
         * 提交后端
         */
        if (TextUtils.isEmpty(sname) || TextUtils.isEmpty(sphone)|| TextUtils.isEmpty(saddress) ) {
            Toast.makeText(this,"姓名手机号地址不能为空",Toast.LENGTH_LONG).show();
        } else {
            if (isSave) {
                PreInfoApi.save(new PreInfo(sname, sphone, saddress), new Callback() {
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
            } else {
                preInfo.setNickname(sname);
                preInfo.setPhone(sphone);
                preInfo.setAddress(saddress);
                PreInfoApi.update(preInfo, new Callback() {
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
            returnPerInfoActivity();
        }
    }

    private void returnPerInfoActivity() {
        Intent intent = new Intent(this, PreInfoActivity.class);
        setResult(ResultActivityCode.UPDATE_ACTIVITY,intent);
        this.finish();
    }

    /**
     * 解析json 获取result
     * @param jsonData
     */
    private void parseJSONWithResult(String jsonData){
        Gson gson = new Gson();
        final Result result = gson.fromJson(jsonData, Result.class);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),result.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public void deletePreInfo(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("是否删除该信息");
        builder.setMessage("昵称:"+preInfo.getNickname()+"\n"+"手机号:"+preInfo.getPhone()+"\n"+"地址："+preInfo.getAddress());
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                PreInfoApi.delete(preInfo.getId(), new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                    }
                });
                returnPerInfoActivity();
            }
        });
        builder.setNegativeButton("取消",null);
        builder.show();
    }
}

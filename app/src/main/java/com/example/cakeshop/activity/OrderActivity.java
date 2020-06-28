package com.example.cakeshop.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.MainActivity;
import com.example.cakeshop.R;
import com.example.cakeshop.adapter.OrderDetailRvAdapter;
import com.example.cakeshop.api.OrderApi;
import com.example.cakeshop.api.PreInfoApi;
import com.example.cakeshop.dao.CartDao;
import com.example.cakeshop.pojo.Order;
import com.example.cakeshop.pojo.OrderDetail;
import com.example.cakeshop.pojo.PreInfo;
import com.example.cakeshop.pojo.result.Result;
import com.example.cakeshop.pojo.result.ResultPreInfo;
import com.example.cakeshop.resultType.ResultActivityCode;
import com.example.cakeshop.resultType.ResultFragmentCode;
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
 * 订单activity
 */
public class OrderActivity extends Activity {

    private RecyclerView rlv;
    private LinearLayout is_not_login;
    private EditText et_name;
    private EditText et_phone;
    private EditText et_address;
    private LinearLayout is_login;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_address;
    private TextView tv_total;
    private CartDao cartDao;
    private SharedPreferences sp;
    private List<OrderDetail> list;
    private OrderDetailRvAdapter orderDetailRvAdapter;
    private boolean checkLogin;
    private double total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        initUi();
    }

   protected void initUi() {
       cartDao=new CartDao(this);
       is_not_login=findViewById(R.id.is_not_login);
       et_name=findViewById(R.id.et_name);
       et_phone=findViewById(R.id.et_phone);
       et_address=findViewById(R.id.et_address);
       is_login=findViewById(R.id.is_login);
       tv_name=findViewById(R.id.tv_name);
       tv_phone=findViewById(R.id.tv_phone);
       tv_address=findViewById(R.id.tv_address);
       tv_total=findViewById(R.id.tv_total);
       rlv=findViewById(R.id.rlv);
       fetchData();
       orderDetailRvAdapter=new OrderDetailRvAdapter(list,this);
       rlv.setLayoutManager(new LinearLayoutManager(this));
       rlv.setAdapter(orderDetailRvAdapter);
       sp=getSharedPreferences("userInfo", Context.MODE_PRIVATE);
       checkLogin= sp.getBoolean("isLogin",false);
       if(checkLogin) {
           is_not_login.setVisibility(View.GONE);
           is_login.setVisibility(View.VISIBLE);
           getPreInfo();
       }
   }

    /**
     * 获取登录最新的预留信息
     */
    private void getPreInfo() {
        PreInfoApi.findByUid(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
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
        ResultPreInfo resultPreInfo = gson.fromJson(jsonData, ResultPreInfo.class);
        if(resultPreInfo.isFlag()) {
            final PreInfo preInfo = resultPreInfo.getData().get(0);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_name.setText(preInfo.getNickname());
                    tv_phone.setText(preInfo.getPhone());
                    tv_address.setText(preInfo.getAddress());
                }
            });
        }
    }


    protected void fetchData() {
       list=cartDao.searchByChecked();
       total = countTotal();
       tv_total.setText("总价为：￥"+total);
   }


    /**
     * 计算价格
     * @return
     */
    private double countTotal() {
        double sum=0;
        for (int i=0;i<list.size();i++) {
            sum += list.get(i).getNum() * list.get(i).getPrice();
        }
        return sum;
    }


    public void toPay(View view) {
        String name="";
        String phone="";
        String address="";
        if(checkLogin) {
            name=tv_name.getText().toString().trim();
            phone=tv_phone.getText().toString().trim();
            address=tv_address.getText().toString().trim();
        } else {
            name=et_name.getText().toString().trim();
            phone=et_phone.getText().toString().trim();
            address=et_address.getText().toString().trim();
        }
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(phone) || TextUtils.isEmpty(address)) {
            Toast.makeText(this,"姓名手机号地址不能为空",Toast.LENGTH_LONG).show();
        } else {

            Order order=new Order();
            order.setUser_id(sp.getInt("id",-1));
            order.setRecevicer(name);
            order.setRecevicer_phone(phone);
            order.setRecevicer_address(address);
            order.setProduct_money(total);
            order.setOrderDetails(list);
            OrderApi.save(order, new Callback() {
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
    }


    /**
     * 解析j'son 转换为 result 类
     * @param jsonData
     */
    private void parseJSONWithResult(String jsonData) {
        Gson gson = new Gson();
        final Result result = gson.fromJson(jsonData, Result.class);
        if(result.isFlag()) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    callBackOrderNo(result.getData());
                }
            });
        }
    }


    /**
     * 返回订单号  剪切板
     * @param orderNo
     */
    private void callBackOrderNo(final String orderNo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(orderNo);
        builder.setTitle("订单号注意保存");
        builder.setPositiveButton("保存剪切板", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ClipboardManager clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData=ClipData.newPlainText(null,orderNo);
                clipboard.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(),"已保存"+orderNo,Toast.LENGTH_LONG).show();
                returnCart();
            }
        });
        builder.show();
    }

    private void returnCart() {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(ResultFragmentCode.CART,intent);
        this.finish();
    }

    /**
     * 跳转 预留信息 activity
     * @param view
     */
    public void selectPreInfo(View view) {
        Intent intent = new Intent(this,PreInfoActivity.class);
        startActivityForResult(intent, ResultActivityCode.TO_NEXT_ACTIVITY);
    }


    /**
     * 重写 返回状态
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (resultCode) {
            case ResultActivityCode.SELECT_ROLLBACK:
                PreInfo preInfo = (PreInfo) data.getSerializableExtra("preInfo");
                tv_name.setText(preInfo.getNickname());
                tv_phone.setText(preInfo.getPhone());
                tv_address.setText(preInfo.getAddress());
                break;
                default:break;
        }
    }

    public void rollback(View view) {
        returnCart();
    }
}

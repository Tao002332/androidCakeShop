package com.example.cakeshop.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.MainActivity;
import com.example.cakeshop.R;
import com.example.cakeshop.adapter.SkuRvAdapter;
import com.example.cakeshop.api.SpuApi;
import com.example.cakeshop.dao.CartDao;
import com.example.cakeshop.pojo.Cart;
import com.example.cakeshop.pojo.Sku;
import com.example.cakeshop.pojo.Spu;
import com.example.cakeshop.pojo.result.ResutlSpuAndSku;
import com.example.cakeshop.resultType.ResultActivityCode;
import com.example.cakeshop.resultType.ResultFragmentCode;
import com.example.cakeshop.utils.AnotherTask;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * created by guigui
 * on 2020/6/28
 */

/**
 * 商品详细展示
 */
public class SpuInfoActivity extends Activity {


    private ImageView iv_img;
    private TextView tv_title;
    private TextView tv_des;
    private TextView tv_discount;
    private TextView tv_pd;
    private TextView tv_expd;
    private RecyclerView rlv;
    private SkuRvAdapter adapter;
    private EditText et_num;

    private Cart cart;
    private CartDao cartDao;

    /**
     * 记录SKU存储
     */
    private Integer skuStock;
    private Double skuPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spuinfo);
        initUI();
    }


    private void initUI() {
        cart=new Cart();
        cartDao=new CartDao(this);
        iv_img=findViewById(R.id.iv_img);
        tv_title=findViewById(R.id.tv_title);
        tv_des=findViewById(R.id.tv_des);
        tv_discount=findViewById(R.id.tv_discount);
        tv_pd=findViewById(R.id.tv_pd);
        tv_expd=findViewById(R.id.tv_expd);
        rlv=findViewById(R.id.rlv);
        et_num=findViewById(R.id.et_num);
        fetchData();
    }

    /**
     * 获取数据
     */
    private void fetchData() {
        int id = getIntent().getIntExtra("spuId", -1);
        SpuApi.getSkus(id, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                parseJSONWithSpuAndSku(jsonData);
            }
        });
    }

    /**
     * 解析json 转换为 resultSpuAndSku 类
     * @param jsonData
     */
    private void parseJSONWithSpuAndSku(String jsonData) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        ResutlSpuAndSku resutlSpuAndSku = gson.fromJson(jsonData, ResutlSpuAndSku.class);
        if (resutlSpuAndSku.isFlag()) {
            final Spu spu = resutlSpuAndSku.getData().getSpu();
            final List<Sku> skuList = resutlSpuAndSku.getData().getSkus();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binderUiData(spu,skuList);
                }
            });
        }

    }

    private void binderUiData(final Spu spu, List<Sku> skuList) {
        String url=spu.getImg();
        new AnotherTask(iv_img).execute(url);
        tv_title.setText(spu.getTitle());
        tv_des.setText(spu.getDesc());
        tv_discount.setText(spu.getDiscount()==1.00?"当前无折扣":spu.getDiscount()*100+"%");
        tv_pd.setText("生产时期是："+spu.getPd());
        tv_expd.setText("有效期截止到："+spu.getExpd());
        cart.setSpu_id(spu.getId());
        cart.setDiscount(spu.getDiscount());

        /**
         * 先设置折扣 然后返回计算
         */
        cart.setPrice(spu.getDiscount());
        cart.setOrigin_price(spu.getPrice());
        cart.setProduct_img(spu.getImg());
        cart.setChecked(true);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SkuRvAdapter(skuList, this, new SkuRvAdapter.Callback() {
            @Override
            public void getStockAndId(Integer id, Integer stock, Double price) {
                cart.setSku_id(id);
                cart.setProduct_title(spu.getTitle()+"-"+id);
                skuStock=stock;
                skuPrice=price;
            }
        });
        rlv.setAdapter(adapter);
    }


    /**
     * 返回按钮 退出该activity
     * @param view
     */
    public void rollback(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        setResult(ResultFragmentCode.INDEX,intent);
        this.finish();
    }

    public void addCart(View view) {
        String num = et_num.getText().toString();
        if (TextUtils.isEmpty(num)) {
            Toast.makeText(this,"请输入商品数量",Toast.LENGTH_LONG).show();
        }
        if(cart.getSku_id() == null) {
            Toast.makeText(this,"请输入商品规格",Toast.LENGTH_LONG).show();
        } else {
            if(Integer.parseInt(num)>skuStock) {
                Toast.makeText(this,"购买商品不能超过库存量",Toast.LENGTH_LONG).show();
            } else {
                cart.setNum(Integer.parseInt(num));
                /**
                 * 计算 折扣后的价格
                 */
                cart.setPrice(skuPrice * cart.getPrice());
            }
            /**
             * 添加购物车
             */
            cartDao.save(cart);
            Toast.makeText(this,"添加成功",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            setResult(ResultFragmentCode.CART,intent);
            this.finish();
        }
    }
}

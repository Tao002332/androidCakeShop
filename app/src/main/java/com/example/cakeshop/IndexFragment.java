package com.example.cakeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.adapter.CateRvAdapter;
import com.example.cakeshop.adapter.SpuRvAdapter;
import com.example.cakeshop.api.CateApi;
import com.example.cakeshop.api.SpuApi;
import com.example.cakeshop.layout.BidirSlidingLayout;
import com.example.cakeshop.pojo.Cate;
import com.example.cakeshop.pojo.ProductSPU;
import com.example.cakeshop.pojo.result.ResultCate;
import com.example.cakeshop.pojo.result.ResultSpu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
 *  主页fragment
 */
public class IndexFragment extends Fragment {

    private BidirSlidingLayout bidirSldingLayout;

    private RecyclerView content_rlv;
    private RecyclerView left_rlv;

    private  EditText et_search;
    private  ImageView iv_search;

    private SpuRvAdapter spuRvAdapter;
    private CateRvAdapter cateRvAdapter;
    private ProductSPU spu;


    /**
     * 填充index layout
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.index_fragment, null);
        initUI(view);
        return view;
    }

    private void initUI(View view) {
        spu = new ProductSPU();
        bidirSldingLayout=view.findViewById(R.id.bidir_sliding_layout);
        content_rlv=view.findViewById(R.id.content_rlv);
        bidirSldingLayout.setScrollEvent(content_rlv);
        left_rlv=view.findViewById(R.id.left_rlv);
        et_search =view.findViewById(R.id.et_search);
        iv_search =view.findViewById(R.id.iv_search);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData();
            }
        });
        fetchLeftData();
        fetchData();
    }

    /**
     * 获取左侧数据
     */
    private void fetchLeftData() {
        CateApi.findAll(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                parseJSONWithCate(jsonData);
            }
        });
    }

    /**
     * 解析json  获取cate
     * @param jsonData
     */
    private void parseJSONWithCate(String jsonData){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        final ResultCate resultCate = gson.fromJson(jsonData, ResultCate.class);
        if (resultCate.isFlag()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binderLeftData(resultCate.getData());
                }
            });
        }
    }



    /**
     * 绑定左侧分类 数据
     */
    private void  binderLeftData(List<Cate> list) {
        cateRvAdapter=new CateRvAdapter(list, getContext(), new CateRvAdapter.Callback() {
            @Override
            public void updateContentData(Integer id) {
                spu.setCate_id(id);
                fetchData();
            }
        });
        left_rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        left_rlv.setAdapter(cateRvAdapter);
    }


    /**
     * 条件查询
     */
    private void searchCondition(){
        String keyword = et_search.getText().toString().trim();
        spu.setKeyword("".equals(keyword)?null:keyword);
    }


    /**
     * 获取主页信息
     */
    private void fetchData() {
        searchCondition();
        SpuApi.search(spu, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String jsonData = response.body().string();
                parseJSONWithSPU(jsonData);
            }
        });
    }


    /**
     * 解析json  获取spu
     * @param jsonData
     */
    private void parseJSONWithSPU(String jsonData){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        final ResultSpu resultSpu = gson.fromJson(jsonData, ResultSpu.class);
        if (resultSpu.isFlag()) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    binderData(resultSpu.getData());
                }
            });
        }
    }


    /**
     * 绑定主页 数据
     * @param list
     */
    private void binderData(List<ProductSPU> list) {
        spuRvAdapter=new SpuRvAdapter(list,getContext());
        if (list.size()%2==0) {
            content_rlv.setLayoutManager(new GridLayoutManager(getContext(),2));
        } else {
            content_rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        content_rlv.setAdapter(spuRvAdapter);
    }

}

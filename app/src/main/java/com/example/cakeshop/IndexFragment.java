package com.example.cakeshop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cakeshop.adapter.ProductSPURvAdapter;
import com.example.cakeshop.api.SpuApi;
import com.example.cakeshop.layout.BidirSlidingLayout;
import com.example.cakeshop.pojo.ProductSPU;
import com.example.cakeshop.pojo.result.ResultSpu;
import com.example.cakeshop.pojo.result.ResultToken;
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

    private RecyclerView rlv;

    private ProductSPURvAdapter adapter;


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
        bidirSldingLayout=view.findViewById(R.id.bidir_sliding_layout);
        rlv=view.findViewById(R.id.rlv);
        fetchData();
    }

    private void fetchData() {
        ProductSPU spu = new ProductSPU();
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



    private void binderData(List<ProductSPU> list) {
        adapter=new ProductSPURvAdapter(list,getContext());
        rlv.setLayoutManager(new LinearLayoutManager(getContext()));
        rlv.setAdapter(adapter);
        bidirSldingLayout.setScrollEvent(rlv);
    }

}

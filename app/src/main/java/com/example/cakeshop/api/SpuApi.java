package com.example.cakeshop.api;

import com.example.cakeshop.pojo.Spu;
import com.example.cakeshop.utils.HttpUtil;

import okhttp3.Callback;

/**
 * created by guigui
 * on 2020/6/27
 */
public class SpuApi {

    private static  final  String  GROUP_NAME="product/";
    private static  final  String  API_NAME="spus";

    static  {
        HttpUtil.init();
    }


    /**
     * 条件查询 商品
     * @param spu
     * @param callback
     */
    public static void search(Spu spu, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/search";
        HttpUtil.enqueuePost(url,spu,callback);
    }

    /**
     *  通过id 获取其下的 sku
     * @param id
     * @param callback
     */
    public static void getSkus(int id,Callback callback) {
        String url=GROUP_NAME+API_NAME+"/"+id+"/getSkus";
        HttpUtil.enqueueGet(url,callback);
    }


}

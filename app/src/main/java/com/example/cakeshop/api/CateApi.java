package com.example.cakeshop.api;

import com.example.cakeshop.utils.HttpUtil;

import okhttp3.Callback;

/**
 * created by guigui
 * on 2020/6/27
 */

/**
 * 商品分类api
 */
public class CateApi {

    private static  final  String  GROUP_NAME="product/";
    private static  final  String  API_NAME="cates";

    static  {
        HttpUtil.init();
    }

    /**
     * 获取全部分类信息
     * @param callback
     */
    public static  void findAll(Callback callback) {
        String url=GROUP_NAME+API_NAME;
        HttpUtil.enqueueGet(url,callback);
    }


}

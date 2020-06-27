package com.example.cakeshop.api;

/**
 * created by guigui
 * on 2020/6/26
 */

import com.example.cakeshop.pojo.Order;
import com.example.cakeshop.utils.HttpUtil;

import okhttp3.Callback;

/**
 * order API  帮助类
 */
public class OrderApi {

    private static  final  String  GROUP_NAME="order/";
    private static  final  String  API_NAME="orders";

    static  {
        HttpUtil.init();
    }

    /**
     * 添加订单
     * @param order
     * @param callback
     */
    public static void save(Order order, Callback callback) {
        String url=GROUP_NAME+API_NAME;
        HttpUtil.enqueuePost(url,order,callback);
    }

    /**
     * 通过 订单号和手机号查询订单
     * @param order
     * @param callback
     */
    public static  void findOrderByOrderNoAndPhone(Order order, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/findOrderByOrderNoAndPhone";
        HttpUtil.enqueuePost(url,order,callback);
    }

    /**
     *  查询用户的订单
     * @param callback
     */
    public static  void findByUid( Callback callback) {
        String url=GROUP_NAME+API_NAME+"/findByUid";
        HttpUtil.enqueueGet(url,callback);
    }

    /**
     *  通过id查看订单详情
     * @param callback
     */
    public static  void findByid( int id, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/"+id;
        HttpUtil.enqueueGet(url,callback);
    }





}

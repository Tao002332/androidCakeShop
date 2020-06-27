package com.example.cakeshop.api;

/**
 * created by guigui
 * on 2020/6/25
 */

import com.example.cakeshop.pojo.PreInfo;
import com.example.cakeshop.pojo.User;
import com.example.cakeshop.utils.HttpUtil;

import okhttp3.Callback;

/**
 * perinfo API  帮助类
 */
public class PreInfoApi {

    private static  final  String  GROUP_NAME="user/";
    private static  final  String  API_NAME="preInfos";

    static  {
        HttpUtil.init();
    }

    /**
            *  获取用户的 预留信息集合
     * @param callback   回调
     */
    public static  void findByUid(Callback callback) {
        String url=GROUP_NAME+API_NAME+"/findByUid";
        HttpUtil.enqueueGet(url,callback);
    }


    /**
     * 添加 预留信息
     */
    public static void save(PreInfo preInfo, Callback callback) {
        String url=GROUP_NAME+API_NAME;
        HttpUtil.enqueuePost(url,preInfo,callback);
    }


    /**
     * 编辑修改预留信息
     */
    public static void update(PreInfo preInfo, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/"+preInfo.getId();
        HttpUtil.enqueuePut(url,preInfo,callback);
    }


    /**
     * 获取单个信息预留信息
     */
    public static void delete(int id, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/"+id;
        HttpUtil.enqueueDelete(url,callback);
    }

}

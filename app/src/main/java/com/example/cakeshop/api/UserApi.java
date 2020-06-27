package com.example.cakeshop.api;

/**
 * created by guigui
 * on 2020/6/25
 */

import com.example.cakeshop.pojo.User;
import com.example.cakeshop.utils.HttpUtil;

import okhttp3.Callback;

/**
 * user API  帮助类
 */
public class UserApi {

    private static  final  String  GROUP_NAME="user/";
    private static  final  String  API_NAME="users";

    static  {
        HttpUtil.init();
    }

    /**
     * 登录
     * @param user       用户实体
     * @param callback   回调
     */
    public static  void login(User user, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/login";
        HttpUtil.enqueuePost(url,user,callback);
    }


    /**
     * 退出登录
     * @param callback   回调
     */
    public static  void logout(Callback callback) {
        String url=GROUP_NAME+API_NAME+"/logout";
        HttpUtil.enqueueGet(url,callback);
    }

    /**
     * 获取个人信息
     * @param callback   回调
     */
    public static  void getInfo(Callback callback) {
        String url=GROUP_NAME+API_NAME+"/getInfo";
        HttpUtil.enqueueGet(url,callback);
    }


    /**
     * 修改个人信息
     * @param user       用户实体
     * @param callback   回调
     */
    public static  void editUserInfo(User user, Callback callback) {
        String url=GROUP_NAME+API_NAME+"/editUserInfo";
        HttpUtil.enqueuePut(url,user,callback);
    }


}

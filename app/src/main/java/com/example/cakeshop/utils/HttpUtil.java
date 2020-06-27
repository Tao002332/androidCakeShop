package com.example.cakeshop.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cakeshop.ContextApplication;
import com.example.cakeshop.MainActivity;
import com.google.gson.Gson;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * created by guigui
 * on 2020/6/25
 */

/**
 * okhttp帮助类
 */
public class HttpUtil {
    private static Gson mGson=new Gson();
    public static final String MEDIA_TYPE="application/json;charset=utf-8";
//    private  static  final  String BASE_URL="http://www.30zwtboot.com:8090/api/";
    private  static  final  String BASE_URL="http://10.0.2.2:8090/api/";
    private static final String METHOD_GET="GET";
    private static final String METHOD_POST="POST";
    private static final String METHOD_PUT="PUT";
    private static final String METHOD_DELETE="DELETE";
    private static OkHttpClient okHttpClient;



    /*
     * 私有化构造方法
     * 使用单例模式
     * */
    public HttpUtil() {
    }

    public static void init(){
        //OkHttpClient.Builder 建造模式 把需要的一些请求要求添加给OkHttpClient
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.readTimeout(3000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(3000,TimeUnit.MILLISECONDS);
        builder.connectTimeout(3000,TimeUnit.MILLISECONDS);
        //OkHttpClient获得OkHttpClient.Builder建造完的OkHttpClient
        okHttpClient=builder.build();
    }
    //处理传给的参数 并指明方法
    private static Request createRequest(String url,String method,Serializable serializable){
        RequestBody requestBody=null;
        if (serializable!=null){
            //将bean类转换成JSON数据
            String bodyJson = mGson.toJson(serializable);
            //媒体类型 指定的结构
            MediaType JSON=MediaType.parse(MEDIA_TYPE);
            //requestBody不是从零中闪出来的 是create出来的
            requestBody=RequestBody.create(JSON,bodyJson);
        }
        //将request建造出来并且将URL传给它 给它指明要访问的URL地址
        Request.Builder builder=new Request.Builder().url(BASE_URL+url);

        // 添加 token 认证
        SharedPreferences sp = ContextApplication.getAppContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        builder.addHeader("Authorization","bearer "+sp.getString("token",null));
        //创建一个空的Request  由switch处理并把request的建造出来 付给request
        Request request=null;
        switch (method){
            case METHOD_GET:
                request=builder.build();
                break;
            case METHOD_POST:
                request=builder.post(requestBody).build();
                break;
            case METHOD_PUT:
                request=builder.put(requestBody).build();
                break;
            case METHOD_DELETE:
                request=builder.delete().build();
                break;
        }
        return request;
    }

    /**
     * request  GET请求
     * @param url       api路径
     * @param callback  回调方法
     */
    public static void enqueueGet(String url, Callback callback){
        Request request = createRequest(url,METHOD_GET,null);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * request POST请求
     * @param url           api路径
     * @param serializable  bean对象
     * @param callback      回调方法
     */
    public static void enqueuePost(String url, Serializable serializable, Callback callback){
        Request request = createRequest(url, METHOD_POST, serializable);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    /**
     * request PUT请求
     * @param url           api路径
     * @param serializable  bean对象
     * @param callback      回调方法
     */
    public static void enqueuePut(String url, Serializable serializable, Callback callback){
        Request request = createRequest(url, METHOD_PUT, serializable);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }


    /**
     * request DELETE请求
     * @param url           api路径
     * @param callback      回调方法
     */
    public static void enqueueDelete(String url, Callback callback){
        Request request = createRequest(url, METHOD_DELETE, null);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
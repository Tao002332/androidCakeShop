package com.example.cakeshop.utils;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * created by guigui
 * on 2020/6/25
 */
public class ParseJson {

    public static  int code;
    public static  boolean flag;
    public static  String message;
    public static String data;


    /**
     * 解析 返回的 数据
     * @param result
     */
    public static void parseJson(String result) {
        try {
            JSONObject jsonObject = new JSONObject(result);
            code = jsonObject.optInt("code");
            flag = jsonObject.optBoolean("flag");
            message = jsonObject.optString("message");
            data = jsonObject.optString("data");
        } catch (JSONException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

}

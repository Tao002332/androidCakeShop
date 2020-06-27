package com.example.cakeshop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 *  帮助类 网络获取图片返回 bitmap
 */
public class ImgDownload {

    public static Bitmap getBitmapByUrl(String imgPath) {
        try {
            URL url = new URL(imgPath);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(1000);
            int statusCode = connection.getResponseCode();
            System.out.println("code +++++"+statusCode);
            if(statusCode==200) {
                InputStream inputStream = connection.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                inputStream.close();
                return bitmap;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

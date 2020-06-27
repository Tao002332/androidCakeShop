package com.example.cakeshop.utils;

/**
 * created by guigui
 * on 2020/6/27
 */

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

/**
 * 异步任务
 */
public class AnotherTask extends AsyncTask<String, Void, Bitmap> {

    private ImageView iv;

    public AnotherTask(ImageView holderImg) {
        iv=holderImg;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        return ImgDownload.getBitmapByUrl(strings[0]);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        iv.setImageBitmap(bitmap);
    }
}
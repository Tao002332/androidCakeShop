package com.example.cakeshop;

import android.app.Application;
import android.content.Context;

/**
 * created by guigui
 * on 2020/6/25
 */

/**
 * 全局 context
 */
public class ContextApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        ContextApplication.context = getApplicationContext();
    }

    public static Context getAppContext(){
        return ContextApplication.context;
    }

}

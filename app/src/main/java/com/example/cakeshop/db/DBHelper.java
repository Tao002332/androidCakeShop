package com.example.cakeshop.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * created by guigui
 * on 2020/6/24
 */
/**
 * db 帮助类
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context ) {
        super(context, "cart.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table tb_cart(" +
                "sku_id Integer primary key," +
                "num Integer," +
                "price decimal(10,2)," +
                "product_title varchar(255)," +
                "product_img varchar(255)," +
                "spu_id Integer," +
                "origin_price decimal(10,2)," +
                "discount decimal(3,2)," +
                "isChecked tinyint(4))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

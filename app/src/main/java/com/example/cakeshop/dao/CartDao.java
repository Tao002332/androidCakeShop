package com.example.cakeshop.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cakeshop.db.DBHelper;
import com.example.cakeshop.pojo.Cart;
import com.example.cakeshop.pojo.OrderDetail;

import java.util.LinkedList;
import java.util.List;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 购物车持久层
 */
public class CartDao {

    private DBHelper dbHelper;

    public CartDao(Context context) {
        this.dbHelper = new DBHelper(context);
    }

    /**
     * 添加购物车
     * @param cart
     * @return
     */
    public boolean save(Cart cart) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("sku_id",cart.getSku_id());
        values.put("num",cart.getNum());
        values.put("price",cart.getPrice());
        values.put("product_title",cart.getProduct_title());
        values.put("product_img",cart.getProduct_img());
        values.put("spu_id",cart.getSpu_id());
        values.put("origin_price",cart.getOrigin_price());
        values.put("discount",cart.getDiscount());
        values.put("isChecked",cart.getChecked()?1:0);
        long result = database.insert("tb_cart", null, values);
        database.close();
        return result>1;
    }

    /**
     * 通过 sku_id 更新
     * @param cart
     * @return
     */
    public boolean update(Cart cart) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("num",cart.getNum());
        values.put("price",cart.getPrice());
        values.put("product_title",cart.getProduct_title());
        values.put("product_img",cart.getProduct_img());
        values.put("spu_id",cart.getSpu_id());
        values.put("origin_price",cart.getOrigin_price());
        values.put("discount",cart.getDiscount());
        values.put("isChecked",cart.getChecked()?1:0);
        int result = database.update("tb_cart", values, "sku_id=?", new String[]{cart.getSku_id() + ""});
        database.close();
        return result>1;
    }


    /**
     * 批量删除
     * @param deleteCart
     */
    public void deleteList(List<Cart> deleteCart) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        try {
            for (Cart cart: deleteCart) {
                database.delete("tb_cart","sku_id=?",new String[]{cart.getSku_id()+""});
            }
            database.setTransactionSuccessful();
        }catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            database.endTransaction();
            database.close();
        }
    }


    /**
     * 删除单个购物车中的商品
     * @param cart
     */
    public void delete(Cart cart) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("tb_cart","sku_id=?",new String[]{cart.getSku_id()+""});
        database.close();
    }



    /**
     *  查询购物车
     * @return
     */
    public List<Cart> findAll() {
        List<Cart> list = new LinkedList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("tb_cart", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int sku_id= cursor.getInt(cursor.getColumnIndex("sku_id"));
            int num= cursor.getInt(cursor.getColumnIndex("num"));
            int spu_id= cursor.getInt(cursor.getColumnIndex("spu_id"));
            double price= cursor.getDouble(cursor.getColumnIndex("price"));
            String product_title= cursor.getString(cursor.getColumnIndex("product_title"));
            String product_img= cursor.getString(cursor.getColumnIndex("product_img"));
            double origin_price= cursor.getDouble(cursor.getColumnIndex("origin_price"));
            double discount= cursor.getDouble(cursor.getColumnIndex("discount"));
            boolean isChecked= cursor.getShort(cursor.getColumnIndex("isChecked"))==1?true:false;
            list.add(new Cart(sku_id, num, price, product_title, product_img, spu_id, origin_price,discount, isChecked));
        }
        cursor.close();
        database.close();
        return list;
    }


    /**
     *  查询购物车  返回订单详情集合
     * @return
     */
    public List<OrderDetail> searchByChecked() {
        List<OrderDetail> list = new LinkedList<>();
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query("tb_cart", null, "isChecked=?", new String[]{ 1+""}, null, null, null);
        while (cursor.moveToNext()) {
            int sku_id= cursor.getInt(cursor.getColumnIndex("sku_id"));
            int num= cursor.getInt(cursor.getColumnIndex("num"));
            double price= cursor.getDouble(cursor.getColumnIndex("price"));
            String product_title= cursor.getString(cursor.getColumnIndex("product_title"));
            String product_img= cursor.getString(cursor.getColumnIndex("product_img"));
            list.add(new OrderDetail(sku_id, num, price, product_title, product_img));
        }
        cursor.close();
        database.close();
        return list;
    }







}

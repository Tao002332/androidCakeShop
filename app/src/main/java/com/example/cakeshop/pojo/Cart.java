package com.example.cakeshop.pojo;

import java.io.Serializable;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 购物车实体类
 */
public class Cart implements Serializable {
    private int sku_id;
    private  int num;
    private double price;
    private  String product_title;
    private  String product_img;
    private int spu_id;
    private double origin_price;
    private double discount;
    private boolean isChecked;


    public Cart() {
    }

    public Cart(int sku_id, int num, double price, String product_title, String product_img, int spu_id, double origin_price, double discount, boolean isChecked) {
        this.sku_id = sku_id;
        this.num = num;
        this.price = price;
        this.product_title = product_title;
        this.product_img = product_img;
        this.spu_id = spu_id;
        this.origin_price = origin_price;
        this.discount = discount;
        this.isChecked = isChecked;
    }


    public int getSku_id() {
        return sku_id;
    }

    public void setSku_id(int sku_id) {
        this.sku_id = sku_id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }

    public int getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(int spu_id) {
        this.spu_id = spu_id;
    }

    public double getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(double origin_price) {
        this.origin_price = origin_price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public boolean getChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }


    @Override
    public String toString() {
        return "Cart{" +
                ", sku_id=" + sku_id +
                ", num=" + num +
                ", price=" + price +
                ", product_title='" + product_title + '\'' +
                ", product_img='" + product_img + '\'' +
                ", spu_id=" + spu_id +
                ", origin_price=" + origin_price +
                ", discount=" + discount +
                ", isChecked=" + isChecked +
                '}';
    }
}

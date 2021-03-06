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
    private Integer sku_id;
    private  Integer num;
    private Double price;
    private  String product_title;
    private  String product_img;
    private Integer spu_id;
    private Double origin_price;
    private Double discount;
    private Boolean isChecked;


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


    public Integer getSku_id() {
        return sku_id;
    }

    public void setSku_id(Integer sku_id) {
        this.sku_id = sku_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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

    public Integer getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Integer spu_id) {
        this.spu_id = spu_id;
    }

    public Double getOrigin_price() {
        return origin_price;
    }

    public void setOrigin_price(Double origin_price) {
        this.origin_price = origin_price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
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

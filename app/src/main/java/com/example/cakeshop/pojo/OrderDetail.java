package com.example.cakeshop.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 订单详情实体类
 */
public class OrderDetail implements Serializable {

    private int id;
    private Date created_at;
    private Date updated_at;

    private Integer order_id;
    private Integer sku_id;

    private Integer num;
    private Double price;

    private String product_title;

    private String product_img;


    public OrderDetail(int sku_id, int num, double price, String product_title, String product_img) {
        this.sku_id = sku_id;
        this.num = num;
        this.price = price;
        this.product_title = product_title;
        this.product_img = product_img;
    }

    public OrderDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", order_id=" + order_id +
                ", sku_id=" + sku_id +
                ", num=" + num +
                ", price=" + price +
                ", product_title='" + product_title + '\'' +
                ", product_img='" + product_img + '\'' +
                '}';
    }
}

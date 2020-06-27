package com.example.cakeshop.pojo;

/**
 * created by guigui
 * on 2020/6/26
 */

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单实体
 */
public class Order implements Serializable {

    private  Integer id;
    private  String  order_no;
    private  Integer user_id;
    private  Integer order_status;
    private  Double product_money;
    private  Integer deliver_type;
    private  String recevicer;
    private  String recevicer_address;
    private  String recevicer_phone;
    private  Integer data_flag;
    private Date created_at;
    private Date updated_at;

    private List<OrderDetail> orderDetails;

    public Order() {
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Order(String order_no, String recevicer_phone) {
        this.order_no = order_no;
        this.recevicer_phone = recevicer_phone;
    }

    public String getCreated_at() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        return formatter.format(created_at);
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss ");
        return formatter.format(updated_at);
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public double getProduct_money() {
        return product_money;
    }

    public void setProduct_money(double product_money) {
        this.product_money = product_money;
    }

    public int getDeliver_type() {
        return deliver_type;
    }

    public void setDeliver_type(int deliver_type) {
        this.deliver_type = deliver_type;
    }

    public String getRecevicer() {
        return recevicer;
    }

    public void setRecevicer(String recevicer) {
        this.recevicer = recevicer;
    }

    public String getRecevicer_address() {
        return recevicer_address;
    }

    public void setRecevicer_address(String recevicer_address) {
        this.recevicer_address = recevicer_address;
    }

    public String getRecevicer_phone() {
        return recevicer_phone;
    }

    public void setRecevicer_phone(String recevicer_phone) {
        this.recevicer_phone = recevicer_phone;
    }

    public int getData_flag() {
        return data_flag;
    }

    public void setData_flag(int data_flag) {
        this.data_flag = data_flag;
    }
}

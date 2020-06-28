package com.example.cakeshop.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * created by guigui
 * on 2020/6/28
 */
public class Sku implements Serializable {
    private Integer id;
    private Date created_at;
    private Date updated_at;
    private Integer spu_id;
    private Double price;
    private Integer stock;
    private String attribute_list;
    private Integer data_flag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getSpu_id() {
        return spu_id;
    }

    public void setSpu_id(Integer spu_id) {
        this.spu_id = spu_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getAttribute_list() {
        return attribute_list;
    }

    public void setAttribute_list(String attribute_list) {
        this.attribute_list = attribute_list;
    }

    public Integer getData_flag() {
        return data_flag;
    }

    public void setData_flag(Integer data_flag) {
        this.data_flag = data_flag;
    }
}

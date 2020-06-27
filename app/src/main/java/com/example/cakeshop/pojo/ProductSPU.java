package com.example.cakeshop.pojo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * created by guigui
 * on 2020/6/27
 */

/**
 * spu 实体
 */
public class ProductSPU implements Serializable {
    private Integer id;
    private Date created_at;
    private Date updated_at;
    private Integer cate_id;
    private String title;
    private String desc;
    private String keyword;
    private String img;
    private Double discount;
    private Double price;
    private Date pd;
    private Date expd;
    private Integer data_flag;
    private Integer pv;

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

    public int getCate_id() {
        return cate_id;
    }

    public void setCate_id(int cate_id) {
        this.cate_id = cate_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getPd() {
        return pd;
    }

    public void setPd(Date pd) {
        this.pd = pd;
    }

    public Date getExpd() {
        return expd;
    }

    public void setExpd(Date expd) {
        this.expd = expd;
    }

    public int getData_flag() {
        return data_flag;
    }

    public void setData_flag(int data_flag) {
        this.data_flag = data_flag;
    }

    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    @Override
    public String toString() {
        return "ProductSPU{" +
                "id=" + id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", cate_id=" + cate_id +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", keyword='" + keyword + '\'' +
                ", img='" + img + '\'' +
                ", discount=" + discount +
                ", price=" + price +
                ", pd=" + pd +
                ", expd=" + expd +
                ", data_flag=" + data_flag +
                ", pv=" + pv +
                '}';
    }
}

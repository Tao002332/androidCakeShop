package com.example.cakeshop.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */
public class Cate implements Serializable {

    private Integer id;
    private Date created_at;
    private Date updated_at;
    private Integer pid;
    private Integer ord;
    private String title;
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

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getOrd() {
        return ord;
    }

    public void setOrd(Integer ord) {
        this.ord = ord;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getData_flag() {
        return data_flag;
    }

    public void setData_flag(Integer data_flag) {
        this.data_flag = data_flag;
    }
}

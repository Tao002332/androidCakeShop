package com.example.cakeshop.pojo;

import java.io.Serializable;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 预留信息实体类
 */
public class PreInfo implements Serializable {
    private  Integer id;
    private  Integer user_id;
    private String nickname;
    private String phone;
    private String address;

    public PreInfo() {
    }

    public PreInfo(String nickname, String phone, String address) {
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
    }

    public PreInfo(int id, int user_id, String nickname, String phone, String address) {
        this.id = id;
        this.user_id = user_id;
        this.nickname = nickname;
        this.phone = phone;
        this.address = address;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PreInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

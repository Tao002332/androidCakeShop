package com.example.cakeshop.pojo;

import java.io.Serializable;

/**
 * created by guigui
 * on 2020/6/24
 */

/**
 * 用户实体类
 */
public class User implements Serializable {

    private int id;
    private  String user_name;
    private  String password;
    private  int data_flag;
    private  String email;
    private  String nickname;

    public User() {
    }

    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }

    public User(int id, String user_name, String password, int data_flag, String email, String nickname) {
        this.id = id;
        this.user_name = user_name;
        this.password = password;
        this.data_flag = data_flag;
        this.email = email;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getData_flag() {
        return data_flag;
    }

    public void setData_flag(int data_flag) {
        this.data_flag = data_flag;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", data_flag=" + data_flag +
                ", email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

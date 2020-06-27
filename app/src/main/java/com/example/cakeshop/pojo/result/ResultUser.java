package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.User;

/**
 * created by guigui
 * on 2020/6/25
 */
public class ResultUser {


    /**
     * code : 20000
     * flag : true
     * message : 成功
     * data : {"id":1,"created_at":"2020-06-12T08:42:18.000000Z","updated_at":"2020-06-12T08:42:18.000000Z","user_name":"guigui","password":"$2y$10$Jki/fvQX5c8IldI3KMLNoOoBqiKwnLo7CXxPkVsIm32nFmYnqqNKG","data_flag":1,"email":"83614153@qq.com","nickname":"用户11"}
     */

    private int code;
    private boolean flag;
    private String message;
    private User data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }
}

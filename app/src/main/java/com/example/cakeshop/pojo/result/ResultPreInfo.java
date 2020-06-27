package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.PreInfo;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/26
 */
public class ResultPreInfo {


    /**
     * code : 20000
     * flag : true
     * message : 查询成功
     * data : [{"id":4,"created_at":"2020-06-23T10:27:35.000000Z","updated_at":"2020-06-23T10:27:35.000000Z","user_id":1,"address":"湖北荆门","phone":"13205023186","nickname":"guigui"},{"id":3,"created_at":"2020-06-13T07:46:55.000000Z","updated_at":"2020-06-13T07:46:55.000000Z","user_id":1,"address":"四川重庆","phone":"13205023186","nickname":"guigui"},{"id":2,"created_at":"2020-06-13T07:46:46.000000Z","updated_at":"2020-06-13T07:46:46.000000Z","user_id":1,"address":"湖南长沙","phone":"13205023186","nickname":"guigui"},{"id":1,"created_at":"2020-06-13T07:46:28.000000Z","updated_at":"2020-06-13T07:46:28.000000Z","user_id":1,"address":"浙江温州","phone":"13205023186","nickname":"啊这"}]
     */

    private int code;
    private boolean flag;
    private String message;
    private List<PreInfo> data;

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


    public List<PreInfo> getData() {
        return data;
    }

    public void setData(List<PreInfo> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultPreInfo{" +
                "code=" + code +
                ", flag=" + flag +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}

package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.Cate;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */
public class ResultCate {
    /**
     * code : 20000
     * flag : true
     * message : 返回集合成功
     * data : [{"id":1,"created_at":"2020-06-10T07:39:44.000000Z","updated_at":"2020-06-10T07:39:44.000000Z","pid":0,"ord":1,"title":"巧克力","data_flag":1},{"id":2,"created_at":"2020-06-10T07:40:22.000000Z","updated_at":"2020-06-10T07:40:22.000000Z","pid":0,"ord":2,"title":"水果","data_flag":1},{"id":3,"created_at":"2020-06-10T15:44:53.000000Z","updated_at":"2020-06-10T15:44:53.000000Z","pid":0,"ord":5,"title":"奶油","data_flag":1},{"id":4,"created_at":"2020-06-10T23:43:14.000000Z","updated_at":"2020-06-10T23:43:14.000000Z","pid":0,"ord":1,"title":"抹茶","data_flag":1},{"id":5,"created_at":"2020-06-10T23:44:09.000000Z","updated_at":"2020-06-15T03:42:41.000000Z","pid":0,"ord":5,"title":"蛋糕","data_flag":1},{"id":6,"created_at":"2020-06-10T23:46:52.000000Z","updated_at":"2020-06-10T23:46:52.000000Z","pid":0,"ord":1,"title":"test","data_flag":1},{"id":7,"created_at":"2020-06-12T07:18:25.000000Z","updated_at":"2020-06-12T07:18:25.000000Z","pid":0,"ord":1,"title":"test02","data_flag":1},{"id":8,"created_at":"2020-06-14T20:13:56.000000Z","updated_at":"2020-06-15T04:14:26.000000Z","pid":0,"ord":11,"title":"1111","data_flag":0}]
     */

    private int code;
    private boolean flag;
    private String message;
    private List<Cate> data;

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

    public List<Cate> getData() {
        return data;
    }

    public void setData(List<Cate> data) {
        this.data = data;
    }

}

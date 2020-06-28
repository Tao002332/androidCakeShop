package com.example.cakeshop.pojo.result;

/**
 * created by guigui
 * on 2020/6/26
 */
public class Result {

    private int code;
    private boolean flag;
    private String message;
    private Object data;

    public String getData() {
        return data.toString();
    }

    public void setData(Object data) {
        this.data = data;
    }

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
}

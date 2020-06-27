package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.Token;

/**
 * created by guigui
 * on 2020/6/25
 */
public class ResultToken {


    /**
     * code : 20000
     * flag : true
     * message : 登录成功
     * data : {"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwOlwvXC8xMC4wLjIuMjo4MDkwXC9hcGlcL3VzZXJcL3VzZXJzXC9sb2dpbiIsImlhdCI6MTU5MzA4OTE4MSwiZXhwIjoxNTkzMDk2MzgxLCJuYmYiOjE1OTMwODkxODEsImp0aSI6ImZFVVlIZWJyVGNrc2lmcUgiLCJzdWIiOjEsInBydiI6ImY2YjcxNTQ5ZGI4YzJjNDJiNzU4MjdhYTQ0ZjAyYjdlZTUyOWQyNGQiLCJyb2xlIjoidXNlciJ9.rmjVECm12t8YdgaVaPQWnuq_Vf5juDf94K3_TKIaRes"}
     */

    private int code;
    private boolean flag;
    private String message;
    private Token data;


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

    public Token getData() {
        return data;
    }

    public void setData(Token data) {
        this.data = data;
    }

}

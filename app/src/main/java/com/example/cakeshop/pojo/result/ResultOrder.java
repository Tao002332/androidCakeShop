package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.Order;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */
public class ResultOrder {


    /**
     * code : 20000
     * flag : true
     * message : 查询成功
     * data : [{"id":16,"created_at":"2020-06-26T05:17:17.000000Z","updated_at":"2020-06-26T05:17:17.000000Z","order_no":"CSA626486373212837","user_id":1,"order_status":-2,"product_money":"260.00","deliver_type":0,"recevicer":"guigui","recevicer_address":"湖北荆门1","recevicer_phone":"13205023186","data_flag":1},{"id":15,"created_at":"2020-06-26T05:14:18.000000Z","updated_at":"2020-06-26T05:14:18.000000Z","order_no":"CSA626484582298229","user_id":1,"order_status":-2,"product_money":"380.00","deliver_type":0,"recevicer":"guigui","recevicer_address":"湖北荆门1","recevicer_phone":"13205023186","data_flag":1},{"id":14,"created_at":"2020-06-26T05:11:19.000000Z","updated_at":"2020-06-26T05:11:19.000000Z","order_no":"CSA626482794924746","user_id":1,"order_status":-2,"product_money":"120.00","deliver_type":0,"recevicer":"guigui","recevicer_address":"湖北荆门1","recevicer_phone":"13205023186","data_flag":1}]
     */

    private int code;
    private boolean flag;
    private String message;
    private List<Order> data;

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

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}

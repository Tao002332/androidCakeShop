package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.Order;
import com.example.cakeshop.pojo.OrderDetail;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/26
 */
public class ResultOrderAndDetail {


    /**
     * code : 20000
     * flag : true
     * message : 查询成功
     * data : {"order":{"id":13,"created_at":"2020-06-18T06:05:12.000000Z","updated_at":"2020-06-18T06:05:12.000000Z","order_no":"CSA618603121173601","user_id":-1,"order_status":-2,"product_money":"99.75","deliver_type":0,"recevicer":"guigui","recevicer_address":"湖北荆门","recevicer_phone":"13205023186","data_flag":1},"orderDetails":[{"id":8,"created_at":"2020-06-18T06:05:12.000000Z","updated_at":"2020-06-18T06:05:12.000000Z","order_id":13,"sku_id":53,"num":5,"price":"11.40","product_title":"蓝莓特选蛋糕-53","product_img":"https://paimgcdn.baidu.com/43562DF2FBF40E04?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F3267062144.jpg&rz=urar_2_968_600&v=0"},{"id":9,"created_at":"2020-06-18T06:05:12.000000Z","updated_at":"2020-06-18T06:05:12.000000Z","order_id":13,"sku_id":54,"num":3,"price":"14.25","product_title":"蓝莓特选蛋糕-54","product_img":"https://paimgcdn.baidu.com/43562DF2FBF40E04?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F3267062144.jpg&rz=urar_2_968_600&v=0"}]}
     */

    private int code;
    private boolean flag;
    private String message;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * order : {"id":13,"created_at":"2020-06-18T06:05:12.000000Z","updated_at":"2020-06-18T06:05:12.000000Z","order_no":"CSA618603121173601","user_id":-1,"order_status":-2,"product_money":"99.75","deliver_type":0,"recevicer":"guigui","recevicer_address":"湖北荆门","recevicer_phone":"13205023186","data_flag":1}
         * orderDetails : [{"id":8,"created_at":"2020-06-18T06:05:12.000000Z","updated_at":"2020-06-18T06:05:12.000000Z","order_id":13,"sku_id":53,"num":5,"price":"11.40","product_title":"蓝莓特选蛋糕-53","product_img":"https://paimgcdn.baidu.com/43562DF2FBF40E04?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F3267062144.jpg&rz=urar_2_968_600&v=0"},{"id":9,"created_at":"2020-06-18T06:05:12.000000Z","updated_at":"2020-06-18T06:05:12.000000Z","order_id":13,"sku_id":54,"num":3,"price":"14.25","product_title":"蓝莓特选蛋糕-54","product_img":"https://paimgcdn.baidu.com/43562DF2FBF40E04?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F3267062144.jpg&rz=urar_2_968_600&v=0"}]
         */

        private Order order;
        private List<OrderDetail> orderDetails;

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        public List<OrderDetail> getOrderDetails() {
            return orderDetails;
        }

        public void setOrderDetails(List<OrderDetail> orderDetails) {
            this.orderDetails = orderDetails;
        }
    }
}

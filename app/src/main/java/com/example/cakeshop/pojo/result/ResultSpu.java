package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.ProductSPU;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/27
 */
public class ResultSpu {

    /**
     * code : 20000
     * flag : true
     * message : 查询成功
     * data : [{"id":13,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","cate_id":1,"title":"蓝莓蛋糕特选","desc":"蓝莓蛋糕蓝莓蛋糕蓝莓蛋糕","keyword":"蓝莓,蛋糕","img":"http://newsblogsite.oss-cn-beijing.aliyuncs.com/HS035-7.jpg","discount":"1.00","price":"30.00","pd":"2020-06-18 14:46:02","expd":"2020-06-25 00:00:00","data_flag":1,"pv":0},{"id":12,"created_at":"2020-06-10T10:51:20.000000Z","updated_at":"2020-06-10T11:19:55.000000Z","cate_id":2,"title":"水果特选蛋糕","desc":"价格实惠,大小适中","keyword":"水果,蛋糕","img":"http://newsblogsite.oss-cn-beijing.aliyuncs.com/%E6%A8%B1%E6%A1%83%E6%9C%A8-05.JPG","discount":"0.95","price":"10.00","pd":"2020-06-10 12:35:42","expd":"2020-06-15 12:35:42","data_flag":1,"pv":0},{"id":11,"created_at":"2020-06-09T10:51:08.000000Z","updated_at":"2020-06-09T10:51:08.000000Z","cate_id":1,"title":"草莓特选蛋糕","desc":"价格实惠,大小适中 11","keyword":"蓝莓,蛋糕","img":"http://newsblogsite.oss-cn-beijing.aliyuncs.com/BB150FCDF96E4F822FC21DC2E5874955.gif","discount":"1.00","price":"5.00","pd":"2020-06-10 12:35:42","expd":"2020-06-15 12:35:42","data_flag":1,"pv":0},{"id":10,"created_at":"2020-06-08T10:50:58.000000Z","updated_at":"2020-06-12T04:22:33.000000Z","cate_id":1,"title":"蓝莓特选蛋糕","desc":"价格实惠,大小适中","keyword":"蓝莓,蛋糕","img":"https://paimgcdn.baidu.com/43562DF2FBF40E04?src=http%3A%2F%2Fms.bdimg.com%2Fdsp-image%2F3267062144.jpg&rz=urar_2_968_600&v=0","discount":"0.95","price":"10.00","pd":"2020-06-10 12:35:42","expd":"2020-06-15 12:35:42","data_flag":1,"pv":0}]
     */

    private int code;
    private boolean flag;
    private String message;
    private List<ProductSPU> data;

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

    public List<ProductSPU> getData() {
        return data;
    }

    public void setData(List<ProductSPU> data) {
        this.data = data;
    }
}

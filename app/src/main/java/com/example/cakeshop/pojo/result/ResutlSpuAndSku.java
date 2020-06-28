package com.example.cakeshop.pojo.result;

import com.example.cakeshop.pojo.Sku;
import com.example.cakeshop.pojo.Spu;

import java.util.List;

/**
 * created by guigui
 * on 2020/6/28
 */
public class ResutlSpuAndSku {


    /**
     * code : 20000
     * flag : true
     * message : 查询成功
     * data : {"spu":{"id":13,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","cate_id":1,"title":"蓝莓蛋糕特选","desc":"蓝莓蛋糕蓝莓蛋糕蓝莓蛋糕","keyword":"蓝莓,蛋糕","img":"http://newsblogsite.oss-cn-beijing.aliyuncs.com/HS035-7.jpg","discount":"1.00","price":"30.00","pd":"2020-06-18 14:46:02","expd":"2020-06-25 00:00:00","data_flag":1,"pv":0},"skus":[{"id":63,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","spu_id":13,"price":"30.00","stock":20,"attribute_list":"{\"size\":5,\"sizeTitle\":\"13寸\",\"height\":1,\"heightTitle\":\"1层\"}","data_flag":1},{"id":64,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","spu_id":13,"price":"40.00","stock":20,"attribute_list":"{\"size\":6,\"height\":22,\"sizeTitle\":\"14寸\",\"heightTitle\":\"4层\"}","data_flag":1}]}
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
         * spu : {"id":13,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","cate_id":1,"title":"蓝莓蛋糕特选","desc":"蓝莓蛋糕蓝莓蛋糕蓝莓蛋糕","keyword":"蓝莓,蛋糕","img":"http://newsblogsite.oss-cn-beijing.aliyuncs.com/HS035-7.jpg","discount":"1.00","price":"30.00","pd":"2020-06-18 14:46:02","expd":"2020-06-25 00:00:00","data_flag":1,"pv":0}
         * skus : [{"id":63,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","spu_id":13,"price":"30.00","stock":20,"attribute_list":"{\"size\":5,\"sizeTitle\":\"13寸\",\"height\":1,\"heightTitle\":\"1层\"}","data_flag":1},{"id":64,"created_at":"2020-06-18T06:46:22.000000Z","updated_at":"2020-06-18T06:46:22.000000Z","spu_id":13,"price":"40.00","stock":20,"attribute_list":"{\"size\":6,\"height\":22,\"sizeTitle\":\"14寸\",\"heightTitle\":\"4层\"}","data_flag":1}]
         */

        private Spu spu;
        private List<Sku> skus;

        public Spu getSpu() {
            return spu;
        }

        public void setSpu(Spu spu) {
            this.spu = spu;
        }

        public List<Sku> getSkus() {
            return skus;
        }

        public void setSkus(List<Sku> skus) {
            this.skus = skus;
        }

    }
}

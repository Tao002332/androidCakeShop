package com.example.cakeshop.pojo;

import java.io.Serializable;

/**
 * created by guigui
 * on 2020/6/28
 */
public class Attribute implements Serializable {

    /**
     * size : 5
     * sizeTitle : 13寸
     * height : 1
     * heightTitle : 1层
     */

    private Integer size;
    private String sizeTitle;
    private Integer height;
    private String heightTitle;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSizeTitle() {
        return sizeTitle;
    }

    public void setSizeTitle(String sizeTitle) {
        this.sizeTitle = sizeTitle;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHeightTitle() {
        return heightTitle;
    }

    public void setHeightTitle(String heightTitle) {
        this.heightTitle = heightTitle;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "size=" + size +
                ", sizeTitle='" + sizeTitle + '\'' +
                ", height=" + height +
                ", heightTitle='" + heightTitle + '\'' +
                '}';
    }
}

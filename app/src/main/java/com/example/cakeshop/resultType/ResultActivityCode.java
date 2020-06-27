package com.example.cakeshop.resultType;

/**
 * created by guigui
 * on 2020/6/24
 */
/**
 * 跳转activity 返回值
 */
public final class ResultActivityCode {
    /**
     * 跳转下一个activity
     */
    public static final int TO_NEXT_ACTIVITY=0;

    /**
     * 返回上一个activity
     */
    public static final int ROLLBACK=1;

    /**
     * 带参数返回activity
     */
    public static final int SELECT_ROLLBACK=2;

    /**
     * 更新activity
     */
    public static final int UPDATE_ACTIVITY=3;
}

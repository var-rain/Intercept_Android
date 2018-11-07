package com.var.intercept_android.utils;

import com.var.intercept_android.data.Storage;
import com.var.intercept_android.exception.NotSignException;

/**
 * Created by: var_rain.
 * Created date: 2018/11/8.
 * Description: 登陆拦截工具类
 */
public class Sign {

    /**
     * 检查是否登陆
     */
    public static void isSign() {
        /* 如果未登录 */
        if (!Storage.isSign) {
            /* 抛出未登录异常 */
            throw new NotSignException();
        }
    }
}

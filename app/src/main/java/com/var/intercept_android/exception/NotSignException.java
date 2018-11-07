package com.var.intercept_android.exception;

/**
 * Created by: var_rain.
 * Created date: 2018/11/8.
 * Description: 未登录异常
 */
public class NotSignException extends RuntimeException {

    /**
     * 构造方法,一个就够了
     */
    public NotSignException() {
        super("用户未登录");
    }
}

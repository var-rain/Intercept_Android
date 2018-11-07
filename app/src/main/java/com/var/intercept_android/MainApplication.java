package com.var.intercept_android;

import android.app.Application;

import com.var.intercept_android.exception.crash.GlobalCrashCapture;

/**
 * Created by: var_rain.
 * Created date: 2018/11/8.
 * Description: 入口点
 */
public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        /* 初始化全局异常捕获,这个很重要,不加这句你的应用就是点哪哪崩 */
        GlobalCrashCapture.instance().init(this);
    }
}

package com.var.intercept_android.exception.crash;

import android.app.Application;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.var.intercept_android.BuildConfig;
import com.var.intercept_android.SignInActivity;
import com.var.intercept_android.exception.NotSignException;

/**
 * Created by: var_rain.
 * Created date: 2018/11/8.
 * Description: 全局异常捕获
 */
public class GlobalCrashCapture implements Thread.UncaughtExceptionHandler {

    /* 静态实例 */
    private static GlobalCrashCapture instance;
    /* 应用程序的上下文参数 */
    private Application context;
    /* looper状态 */
    private boolean running = true;

    /**
     * 构造方法
     */
    private GlobalCrashCapture() {
    }

    /**
     * 初始化
     *
     * @param context 上下文参数
     */
    public void init(Application context) {
        this.context = context;
        this.looperException();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 获取当前对象的静态实例
     *
     * @return 返回 {@link GlobalCrashCapture} 对象
     */
    public static GlobalCrashCapture instance() {
        if (GlobalCrashCapture.instance == null) {
            GlobalCrashCapture.instance = new GlobalCrashCapture();
        }
        return GlobalCrashCapture.instance;
    }

    /**
     * 重点在这里,正常情况下主线程的异常就被捕获也会导致虚拟机停止,为了不让虚拟机停止(简单来说是不让APP崩溃)
     * 最好的方法就是接管Android的Looper,然后捕获异常
     * <p>
     * 接管Android的Looper并捕获异常
     */
    private void looperException() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    try {
                        Looper.loop();
                    } catch (Throwable e) {
                        /* 如果是自己定义的异常 */
                        if (e instanceof NotSignException) {
                            /* 那就自己去处理吧 */
                            handleNotSignException();
                        } else {
                            /* 如果不是自己抛出的异常,那就用全局异常捕获去处理 */
                            handleException(e);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        this.handleException(e);
    }

    /**
     * 默认异常信息处理
     *
     * @param ex 异常信息
     */
    private void handleException(final Throwable ex) {
        ex.printStackTrace();
        new Thread() {
            @Override
            public void run() {

                /* 弹个Toast告诉你程序出问题了 */
                Looper.prepare();
                if (BuildConfig.DEBUG) {
                    Toast.makeText(context, "哦豁,程序问题咯: " + ex.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
                Looper.loop();

                /* 等三秒 */
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /* 然后结束应用程序 */
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        }.start();
    }

    /**
     * 未登录异常处理
     */
    private void handleNotSignException() {
        /* 其实你可以定义一个接口,然后使用回调的方式处理,此处为了简单明了的说明问题,不做详细解释 */
        context.startActivity(new Intent(context, SignInActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}

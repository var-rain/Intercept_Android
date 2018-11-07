package com.var.intercept_android;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.var.intercept_android.data.Storage;

/**
 * Created by: var_rain.
 * Created date: 2018/11/8.
 * Description: 登陆界面
 */
public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setTitle("登陆");

        /* 设置点击事件 */
        findViewById(R.id.sign_in_but).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* 假装登陆成功 */
                Storage.isSign = true;

                finish();
            }
        });
    }
}

package com.var.intercept_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.var.intercept_android.utils.Sign;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 设置点击事件 */
        findViewById(R.id.start_to_home).setOnClickListener(new View.OnClickListener() {

            /**
             * 你会看到很神奇的一幕,当 {@link com.var.intercept_android.data.Storage} 中的
             * {@link com.var.intercept_android.data.Storage#isSign} 为false的时候.跳转到
             * {@link HomeActivity} 的代码并没有执行
             */
            @Override
            public void onClick(View v) {

                /* 首先检查用户是否登陆 */
                Sign.isSign();

                /* 然后跳转到HomeActivity */
                startActivity(new Intent(MainActivity.this, HomeActivity.class));
            }
        });
    }
}

package com.example.anxiao.mytestapplication.lesson_http;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;

import http.EnqueueCallBack;
import http.RestClient;
import http.bean.UserRequestBean;
import http.bean.UserResponseBean;

public class HttpTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        UserRequestBean bean = new UserRequestBean();
        bean.setPassword("666666");
        bean.setUsername("2");

        final long startTime = System.currentTimeMillis();
        Logger.DEBUG("start : " + startTime);
        RestClient.SERVICES().userInfo(bean)
                .enqueue(new EnqueueCallBack<UserResponseBean>() {
                    @Override
                    public void _finish() {
                        long endTime = System.currentTimeMillis();
                        Logger.DEBUG("end : " + endTime);
                        Logger.DEBUG("spend : " + (endTime - startTime));
                    }

                    @Override
                    protected void _success(UserResponseBean response) {
                        Logger.DEBUG(response.getName());
                    }

                    @Override
                    protected void _err(String err) {
                        Logger.DEBUG(err);
                    }
                });


    }

}

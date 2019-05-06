package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.anmnight.commlibrary.watcher.IProxyClickListener;
import com.anmnight.commlibrary.watcher.Watcher;

public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d("start", getPackageName());


        Button button = findViewById(R.id.button);
        button.setOnClickListener((view) -> Log.i(tag, "setOnClickListener"));


    }





}

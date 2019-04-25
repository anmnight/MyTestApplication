package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;


public class SplashActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d("start", getPackageName());


    }



}

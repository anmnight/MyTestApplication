package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.Nullable;


public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackground(getResources().getDrawable(R.drawable.gal));
        setContentView(R.layout.activity_splash);


    }

}

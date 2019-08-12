package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.example.testapp.activities.DampLayoutActivity;

public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackground(getResources().getDrawable(R.drawable.gal));
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, DampLayoutActivity.class));
        finish();
    }

}

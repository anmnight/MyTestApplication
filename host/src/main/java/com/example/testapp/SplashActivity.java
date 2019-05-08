package com.example.testapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.testapp.activities.CoordinatorActivity;
import com.example.testapp.activities.RoundImageEntryActivity;

public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        findViewById(R.id.coor).setOnClickListener((view)-> startActivity(new Intent(this, CoordinatorActivity.class)));

        findViewById(R.id.round).setOnClickListener((view)-> startActivity(new Intent(this, RoundImageEntryActivity.class)));
    }
}

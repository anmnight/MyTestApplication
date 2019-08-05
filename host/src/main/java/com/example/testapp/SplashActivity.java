package com.example.testapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.example.testapp.activities.CoordinatorActivity;
import com.example.testapp.activities.RoundImageEntryActivity;
import com.example.testapp.activities.TestTouchActivity;
import com.example.testapp.services.TestService;
import com.example.testapp.test.DataUnit;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setBackground(getResources().getDrawable(R.drawable.gal));
        setContentView(R.layout.activity_splash);
        startActivity(new Intent(this, TestTouchActivity.class));
        finish();
    }

}

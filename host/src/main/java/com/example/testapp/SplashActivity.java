package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;

public class SplashActivity extends Activity {

    private String tag = "SplashActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d("start", getPackageName());


        test();

    }

    private void test() {
        Log.i(tag, "im origin SplashActivity");
    }


}

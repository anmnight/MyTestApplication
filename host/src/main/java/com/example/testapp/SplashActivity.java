package com.example.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.idescout.sql.SqlScoutServer;


public class SplashActivity extends Activity {

    private SqlScoutServer sqlScoutServer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Log.d("start", getPackageName());

        sqlScoutServer = SqlScoutServer.create(this, getPackageName());

    }


    @Override
    protected void onResume() {
        super.onResume();

        sqlScoutServer.resume();
    }


    @Override
    protected void onPause() {
        super.onPause();

        sqlScoutServer.pause();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        sqlScoutServer.destroy();
    }
}

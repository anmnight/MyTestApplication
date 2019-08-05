package com.example.testapp.services;

import android.app.Dialog;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class TestService extends Service {

    private Dialog mLoadingDialog;

    public TestService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();

    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}

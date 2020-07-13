package com.example.testapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class RemoteService extends Service {

    private final String tag = "MyServices";
    private final IBinder binder = new RemoteBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(tag, "onCreate");
    }


    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(tag, "onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(tag, "onDestroy");
    }

    public class RemoteBinder extends Binder {
        public RemoteService getService() {
            return RemoteService.this;
        }
    }

    public void doSomething() {
        Log.i(tag, "doSomething...");
    }
}

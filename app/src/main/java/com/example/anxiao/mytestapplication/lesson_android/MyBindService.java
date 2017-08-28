package com.example.anxiao.mytestapplication.lesson_android;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;

import com.example.anxiao.mytestapplication.Logger;

public class MyBindService extends Service {

    private HandlerThread thread;

    @Override
    public void onCreate() {
        super.onCreate();
        thread = new HandlerThread("asda");
        thread.start();
        Handler handler = new Handler(thread.getLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                boolean exit = false;
                while (!exit) {
                    try {
                        Thread.sleep(1000);
                        mAction.onProgress(i);
                        i += 1;
                    } catch (InterruptedException e) {
                        exit = true;
                    }
                }
            }
        });
    }

    public interface MyServiceAction {
        void onProgress(int progress);
    }

    private MyServiceAction mAction;

    public void setListener(MyServiceAction action) {
        this.mAction = action;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        thread.interrupt();
        return super.onUnbind(intent);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    public class MyBind extends Binder {
        public MyBindService getService() {
            return MyBindService.this;
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Logger.err("on destroy..");
    }


}

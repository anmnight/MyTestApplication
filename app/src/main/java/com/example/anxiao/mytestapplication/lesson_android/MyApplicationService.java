package com.example.anxiao.mytestapplication.lesson_android;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

import com.example.anxiao.mytestapplication.HomeApplication;
import com.example.anxiao.mytestapplication.Logger;

public class MyApplicationService extends Service {
    public MyApplicationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        throw new UnsupportedOperationException("Not yet implemented");
    }

    private HandlerThread thread;

    @Override
    public void onCreate() {
        super.onCreate();

        Logger.info("is on main : " + HomeApplication.getInstance().isOnMainProcess());

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
                        Logger.debug("IM APP SERVICE : " + i);
                        if (i == 5) {
                            startMap();
                        }
                        i += 1;
                    } catch (InterruptedException e) {
                        exit = true;
                    }
                }
            }
        });
    }

    private void startMap() {
        Intent intent = new Intent("com.example.anxiao.mytestapplication.TESTMAP", Uri.parse("info://im from service"));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }
}

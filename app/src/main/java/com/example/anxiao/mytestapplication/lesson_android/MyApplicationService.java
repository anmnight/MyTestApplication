package com.example.anxiao.mytestapplication.lesson_android;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;

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
                        i += 1;
                    } catch (InterruptedException e) {
                        exit = true;
                    }
                }
            }
        });
    }


    @Override
    public void onDestroy() {
        thread.interrupt();
        super.onDestroy();
    }
}

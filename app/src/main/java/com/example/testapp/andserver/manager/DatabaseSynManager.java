package com.example.testapp.andserver.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

public class DatabaseSynManager {

    private LocalCardInfoManager mLocalCardInfoManager;
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    private Callback callback;

    public DatabaseSynManager(Context context, Callback callback) {
        this.mLocalCardInfoManager = new LocalCardInfoManager(context);
        this.callback = callback;
    }

    //todo 公共资源 异步初始化 处理器
    //todo 初始化公共文件夹

    public void localSyn() {

        new Thread(() -> {
            try {
                new OnStartRunnable().run();
                mLocalCardInfoManager.readInfo();
                mLocalCardInfoManager.readFace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                new OnEndRunnable().run();
            }

        }).start();
    }

    private class OnStartRunnable implements Runnable {

        @Override
        public void run() {
            mainHandler.post(() -> callback.onStart());
        }
    }


    private class OnEndRunnable implements Runnable {

        @Override
        public void run() {
            mainHandler.post(() -> callback.onEnd());
        }
    }


    public interface Callback {

        public void onStart();

        public void onEnd();
    }
}

package com.example.testapp.app;

import android.os.Handler;
import android.os.HandlerThread;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private Handler mHandler;

    private static class CrashHandlerHolder {
        static CrashHandler crashHandler = new CrashHandler();
    }

    public static CrashHandler instance() {
        return CrashHandlerHolder.crashHandler;
    }

    public CrashHandler() {
        HandlerThread thread = new HandlerThread("toast_thread");
        thread.start();
        mHandler = new Handler(thread.getLooper());
    }

    public void init() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {

        if (!handlerException(throwable) && mUncaughtExceptionHandler != null) {
            mUncaughtExceptionHandler.uncaughtException(thread, throwable);
        } else {
            //不可处理的问题，关闭应用

            // TODO: 2017/9/7 upload exception
        }

    }

    private boolean handlerException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        } else {
            throwable.printStackTrace();
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    ToastUnit.sortToase("app will be close...");
                }
            });
            return true;
        }
    }
}

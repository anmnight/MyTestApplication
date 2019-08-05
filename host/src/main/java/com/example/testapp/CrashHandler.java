package com.example.testapp;

import android.os.Handler;
import android.os.HandlerThread;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private static class CrashHandlerHolder {
        static CrashHandler crashHandler = new CrashHandler();
    }

    public static CrashHandler INSTANCE() {
        return CrashHandlerHolder.crashHandler;
    }

    private CrashHandler() {
        HandlerThread thread = new HandlerThread("toast_thread");
        thread.start();
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
            System.out.println(throwable.getMessage());
            TestHomeApplication.finishApp();
        }

    }

    private boolean handlerException(final Throwable throwable) {
        if (throwable == null) {
            return false;
        } else {
            throwable.printStackTrace();
            return true;
        }
    }
}

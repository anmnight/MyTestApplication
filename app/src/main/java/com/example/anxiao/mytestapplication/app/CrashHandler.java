package com.example.anxiao.mytestapplication.app;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    private static class CrashHandlerHolder {
        static CrashHandler crashHandler = new CrashHandler();
    }

    public static CrashHandler instance(){
        return CrashHandlerHolder.crashHandler;
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
            ToastUnit.sortToase("app will be close...");
            throwable.printStackTrace();
        }

    }

    private boolean handlerException(Throwable throwable) {
        if (throwable == null) {
            return false;
        } else {
            Logger.err(CrashHandler.class, throwable.getMessage());
            ToastUnit.sortToase(throwable.getMessage());
            return true;
        }
    }
}

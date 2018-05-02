package com.example.testapp.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Process;

public class HomeApplication extends Application implements Application.ActivityLifecycleCallbacks {

    private static HomeApplication application;

    public static HomeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        if (isOnMainProcess()) {
            CrashHandler.instance().init();
        }
    }






    /**
     * 获取当前pid的进程名
     *
     * @param context
     * @param pid
     * @return
     */
    private String getApplicationName(Context context, int pid) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : manager.getRunningAppProcesses()) {
            if (info.pid == pid) {
                return info.processName;
            }
        }
        return "";
    }

    private final static String PROCESS_NAME = "com.example.testapp";

    public boolean isOnMainProcess() {
        int pid = Process.myPid();
        String process = getApplicationName(application, pid);
        return process.isEmpty() || process.equalsIgnoreCase(PROCESS_NAME);
    }


    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}

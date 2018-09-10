package com.example.testapp;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.Process;

import com.bankcomm.commlibrary.logger.LogFactory;
import com.bankcomm.commlibrary.logger.Logger;
import com.bankcomm.commlibrary.view.ActivityAdapter;
import com.bankcomm.commlibrary.view.ActivityAdapterFactory;
import com.example.testapp.view.bound.WechatHeader;

public class TestHomeApplication extends Application implements Application.ActivityLifecycleCallbacks {

    public static TestHomeApplication application;

    public ActivityAdapter mViewAdapter;

    public WechatHeader mHeader;

    public Logger mLogger;

    public static TestHomeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;


        if (isOnMainProcess()) {
            CrashHandler.instance().init();

            //注册自适配View
            mViewAdapter = ActivityAdapterFactory.Builder.
                    INSTANCE.
                    setType(ActivityAdapterFactory.Type.WIDTH)
                    .build(1080, 1920, 420, application);

            //bound header
            mHeader = new WechatHeader(this);

            //register log
            mLogger = LogFactory.Builder.INSTANCE.initLogService(this);


        }
    }

    public boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    private String getApplicationName(Context context, int pid) {
        ActivityManager manager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo info : manager.getRunningAppProcesses()) {
            if (info.pid == pid) {
                return info.processName;
            }
        }
        return "";
    }

    private final static String PROCESS_NAME = "com.anmnight.testapp";

    public boolean isOnMainProcess() {
        int pid = Process.myPid();
        String process = getApplicationName(TestHomeApplication.this, pid);
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

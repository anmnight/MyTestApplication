package com.anmnight.commlibrary;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class BaseApplication extends Application {

    private boolean isBackground;

    @Override
    public void onCreate() {
        super.onCreate();

        listenForForeground();
        listenForScreenTurningOff();

    }

    private void listenForForeground() {

        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                Log.e("tag", activity.getClass().getSimpleName() + "---onActivityCreated");


            }

            @Override
            public void onActivityStarted(Activity activity) {
                Log.e("tag", activity.getClass().getSimpleName() + "---onActivityStarted");
            }

            @Override
            public void onActivityResumed(Activity activity) {
                Log.e("tag", activity.getClass().getSimpleName() + "---onActivityResumed");
                if (isBackground) {
                    notifyForeground();
                }
            }

            @Override
            public void onActivityPaused(Activity activity) {
                Log.e("tag", activity.getClass().getSimpleName() + "---onActivityPaused");

            }

            @Override
            public void onActivityStopped(Activity activity) {
                Log.e("tag", activity.getClass().getSimpleName() + "---onActivityStopped");
                isApplicationBroughtToBackground(activity);
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                Log.e("tag", activity.getClass().getSimpleName() + "---onActivityDestroyed");

            }
        });
    }

    private void listenForScreenTurningOff() {//锁屏
        IntentFilter screenStateFilter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                notifyBackground();
            }
        }, screenStateFilter);
    }

    private void notifyForeground() {
        isBackground = false;
        Log.e("tag", "---重新使用应用");

    }

    private void notifyBackground() {
        isBackground = true;
        Log.e("tag", "---后台");

    }


    /**
     * 判断当前应用程序处于前台还是后台
     */
    public boolean isApplicationBroughtToBackground(Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (!topActivity.getPackageName().equals(context.getPackageName())) {
                notifyBackground();
                return true;
            }
        }
        return false;

    }

    public boolean isBackground() {
        return isBackground;
    }
}

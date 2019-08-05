package com.anmnight.commlibrary.watcher;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.util.Log;

public class CustomInstrumentation extends Instrumentation {
    private Instrumentation mBase;
    private String tag = "CustomInstrumentation";

    public CustomInstrumentation(Instrumentation base) {
        this.mBase = base;
    }


    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Log.d(tag, "哈哈，你被Hook了");
        Log.d(tag, "className=" + className + " intent=" + intent);
        return mBase.newActivity(cl, className, intent);
    }





    @Override
    public void callActivityOnResume(Activity activity) {
        super.callActivityOnResume(activity);
        Log.d(tag, "callActivityOnResume : " + activity.getLocalClassName());
    }


    @Override
    public void callActivityOnPause(Activity activity) {
        super.callActivityOnPause(activity);
        Log.d(tag, "callActivityOnResume : " + activity.getLocalClassName());
    }
}
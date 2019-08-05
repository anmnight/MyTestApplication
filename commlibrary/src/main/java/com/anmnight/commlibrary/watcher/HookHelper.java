package com.anmnight.commlibrary.watcher;

import android.app.Instrumentation;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class HookHelper {

    private static String tag = "HookHelper";

    public static void hookInstrumentation() throws Exception {
        Class activityThread = Class.forName("android.app.ActivityThread");
        Method currentActivityThread = activityThread.getDeclaredMethod("currentActivityThread");
        currentActivityThread.setAccessible(true);

        Object activityThreadObject = currentActivityThread.invoke(null);


        Field mInstrumentation = activityThread.getDeclaredField("mInstrumentation");
        mInstrumentation.setAccessible(true);
        Instrumentation instrumentation = (Instrumentation) mInstrumentation.get(activityThreadObject);
        CustomInstrumentation customInstrumentation = new CustomInstrumentation(instrumentation);

        mInstrumentation.set(activityThreadObject, customInstrumentation);
        Log.d(tag, "Hook Instrumentation成功");

    }
}

package com.example.testapp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.Process;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.testapp.watcher.HookHelper;

import java.util.Stack;

public class TestHomeApplication extends Application {

    private static TestHomeApplication application;
    private String tag = "TestHomeApplication";

    public static TestHomeApplication getInstance() {
        return application;
    }

    private static Stack<AppCompatActivity> activities = new Stack<>();

    public static void finishApp() {
        for (AppCompatActivity activity : activities) {
            activity.finish();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;


        try {
            HookHelper.hookInstrumentation();
        } catch (Exception e) {
            e.printStackTrace();
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


    public String test() {
        EditText et = new EditText(this);
        String text = et.getText().toString();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            String t = String.valueOf(text.charAt(i));
            temp.append(t);
            temp.append(",");
        }
        return temp.substring(0, temp.length() - 1);
    }


}

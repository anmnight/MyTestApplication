package com.example.testapp;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.Process;

import com.anmnight.commlibrary.view.ActivityAdapter;
import com.anmnight.commlibrary.view.ActivityAdapterFactory;
import com.example.testapp.andserver.database.TestAppDatabase;
import com.facebook.stetho.Stetho;

import androidx.room.Room;

public class TestHomeApplication extends Application {

    private static TestHomeApplication application;

    public ActivityAdapter mViewAdapter;

    public TestAppDatabase database;


    public static TestHomeApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;


        if (isOnMainProcess()) {
            CrashHandler.instance().init();

            //database
            database = Room.databaseBuilder(this, TestAppDatabase.class, "testapp_database").build();

            Stetho.initializeWithDefaults(this);

            //注册自适配View
            mViewAdapter = new ActivityAdapterFactory.Builder()
                    .setType(ActivityAdapterFactory.Type.WIDTH)
                    .build(1080, 1920, 420, application);


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

}

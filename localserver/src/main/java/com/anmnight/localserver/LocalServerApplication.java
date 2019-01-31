package com.anmnight.localserver;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.os.Process;

import com.anmnight.commlibrary.view.ActivityAdapter;
import com.anmnight.commlibrary.view.ActivityAdapterFactory;
import com.facebook.stetho.Stetho;

import androidx.room.Room;

public class LocalServerApplication extends Application {

    private static LocalServerApplication application;

    public LocalServerDatabase database;


    public static LocalServerApplication getInstance() {
        return application;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        application = this;

        //database
        database = Room.databaseBuilder(this, LocalServerDatabase.class, "testapp_database").build();

        Stetho.initializeWithDefaults(this);
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
        String process = getApplicationName(LocalServerApplication.this, pid);
        return process.isEmpty() || process.equalsIgnoreCase(PROCESS_NAME);
    }

}

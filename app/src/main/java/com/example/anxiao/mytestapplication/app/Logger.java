package com.example.anxiao.mytestapplication.app;

import android.util.Log;


public class Logger {

    public static void debug(Object log) {
        Log.d("anmnight", String.valueOf(log));
    }

    public static void info(Object log) {
        Log.i("anmnight", String.valueOf(log));
    }

    public static void err(Object log) {
        Log.e("anmnight", String.valueOf(log));
    }

    public static void err(Class clz, Object log) {
        Log.e(clz.getName(), String.valueOf(log));
    }

}

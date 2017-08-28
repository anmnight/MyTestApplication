package com.example.anxiao.mytestapplication;

import android.widget.Toast;

/**
 * Created by anxiao on 2017/8/25.
 */

public class ToastUnit {
    public static void sortToase(String msg) {
        Toast.makeText(HomeApplication.getInstance(), msg, Toast.LENGTH_SHORT).show();
    }
}

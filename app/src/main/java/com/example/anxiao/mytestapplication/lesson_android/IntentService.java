package com.example.anxiao.mytestapplication.lesson_android;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by anxiao on 2017/7/25.
 */

public class IntentService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

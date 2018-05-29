package com.example.testapp.lesson_android

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.testapp.BackgroundTaskInterface

class RemoteService : Service() {
    override fun onBind(intent: Intent?): IBinder {
        return mIBinder
    }

    val mIBinder = object :BackgroundTaskInterface.Stub(){
        override fun basicTypes(anInt: Int, aLong: Long, aBoolean: Boolean, aFloat: Float, aDouble: Double, aString: String?) {

        }

    }
}

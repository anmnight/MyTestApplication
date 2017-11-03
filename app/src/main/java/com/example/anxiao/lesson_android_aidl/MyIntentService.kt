package com.example.anxiao.lesson_android_aidl

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.anxiao.app.Logger
import java.util.*

/**
 * An [IntentService] subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 *
 *
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
class MyIntentService : Service() {

    var mAction: ITestAction? = null
    private val mBinder = object : IService.Stub() {
        override fun unRegisterTestAction(action: ITestAction?) {

        }

        override fun registerTestAction(action: ITestAction?) {
            mAction = action!!
        }
    }


    var temp = 1
    val timer = Timer()
    override fun onCreate() {
        super.onCreate()

        Logger.debug("onCreate")

        timer.schedule(object : TimerTask() {
            override fun run() {
                temp += 1
                Logger.debug("Servicing : " + temp)
                mAction?.nowTime(temp.toLong())
            }
        }, 0, 1000)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        timer.cancel()
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

}

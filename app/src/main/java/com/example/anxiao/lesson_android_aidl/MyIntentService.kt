package com.example.anxiao.lesson_android_aidl

import android.app.IntentService
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
class MyIntentService : IntentService("MyIntentService") {
    override fun onHandleIntent(p0: Intent?) {

    }

    var mAction :ITestAction?=null
    private val mBinder = object: IService.Stub() {
        override fun unRegisterTestAction(action: ITestAction?) {

        }

        override fun registerTestAction(action: ITestAction?) {
            Logger.err("registered to server")
            mAction = action!!
        }
    }

    var temp = 1
    override fun onCreate() {
        super.onCreate()

        Timer().schedule(object : TimerTask() {
            override fun run() {
                temp+=1
                Logger.err("running.. ： "+temp)
                mAction?.nowTime(temp.toLong())
            }
        },2000,1000)
    }



    override fun onBind(intent: Intent?): IBinder {
        return mBinder
    }

}

package com.example.anxiao.lesson_android_aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import com.example.anxiao.app.Logger
import com.example.anxiao.mytestapplication.R

import kotlinx.android.synthetic.main.activity_aidl_test.*

class TestAIDLActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aidl_test)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {
            val intent = Intent()
            intent.action = "com.anmnight.aidl.service"
            bindService(intent, conn, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.debug("Activity onDestroy")
        unbindService(conn)
    }

    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val mBinder = IService.Stub.asInterface(p1)
            mBinder.registerTestAction(action)
        }

    }

    val action = object : ITestAction.Stub() {
        override fun nowTime(timeSeed: Long) {
            Logger.info("Time : " + timeSeed)
        }
    }

}

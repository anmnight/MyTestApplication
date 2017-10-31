package com.example.anxiao.lesson_android_aidl

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.anxiao.app.Logger
import com.example.anxiao.app.ToastUnit
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
            bindService(intent,mBind, Context.BIND_AUTO_CREATE)
//            startService(intent)

        }
    }

    private val mBind = object :ServiceConnection{
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            val mBinder = IService.Stub.asInterface(p1)
            mBinder.registerTestAction(action)
        }

    }

    val action = object : ITestAction.Stub(){
        override fun nowTime(timeSeed: Long) {
           Logger.info("Time : " + timeSeed)
        }
    }

}

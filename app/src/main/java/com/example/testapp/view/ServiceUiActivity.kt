package com.example.testapp.view

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import com.example.testapp.android.UiService
import com.example.testapp.mytestapplication.R
import unit.Logger

class ServiceUiActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_ui)


    }


    fun getUiService() {

        bindService(Intent(this@ServiceUiActivity, UiService::class.java), conn, Context.BIND_AUTO_CREATE)
    }

    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {

        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

        }

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {

        Logger.error("Activity dispatchTouchEvent : ${super.dispatchTouchEvent(ev)}")

        return false
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        Logger.error("Activity onTouchEvent : ${super.onTouchEvent(event)}")

        return true
    }


}

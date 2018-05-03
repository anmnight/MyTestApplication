package com.example.testapp.view

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.example.testapp.lesson_android.UiService
import com.example.testapp.mytestapplication.R

class ServiceUiActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_ui)


    }


    val listener = object :View.OnClickListener{
        override fun onClick(p0: View?) {
            when(p0?.id){

            }
        }

    }


    fun getUiService() {

        bindService(Intent(this@ServiceUiActivity, UiService::class.java), conn, Context.BIND_AUTO_CREATE)
    }

    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {

        }

    }

}

package com.example.testapp.lesson_android

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.example.testapp.app.Logger
import com.example.testapp.mytestapplication.R
import kotlinx.android.synthetic.main.content_service.*

class ServiceActivity : AppCompatActivity() {

    private var locThread: Thread? = null


    private var serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            val bind = iBinder as MyBindService.MyBind
            bind.service.setListener { progress -> runOnUiThread {
                bind_time.text = progress.toString() } }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            Logger.debug("unbind service")
        }
    }

    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)


        locThread = Thread(Runnable {
            var i = 0
            var exit = false
            while (!exit) {
                try {
                    Thread.sleep(1000)
                    Logger.debug("IM THREAD : $i")
                    i += 1
                } catch (e: InterruptedException) {
                    exit = true
                }

            }
        })
        locThread!!.start()


    }

    private fun onBindService() {
        val intent = Intent(this, MyBindService::class.java)
        isRunning = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
    }

    private fun onUnbindService() {
        if (isRunning) {
            isRunning = false
            unbindService(serviceConnection)
        }
    }


    override fun onDestroy() {
        onUnbindService()
        super.onDestroy()
        locThread!!.interrupt()

    }

}

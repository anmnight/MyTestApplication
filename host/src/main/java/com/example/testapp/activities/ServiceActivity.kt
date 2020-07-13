package com.example.testapp.activities

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.R
import com.example.testapp.services.LocalService
import com.example.testapp.services.RemoteService
import kotlinx.android.synthetic.main.activity_service.*

class ServiceActivity : AppCompatActivity() {

    private var mLocalService: LocalService? = null
    private var mMessenger: Messenger? = null
    private var mRemoteService: RemoteService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)


        messenger_bind.setOnClickListener {
            bindService(buildMessengerIntent(), messengerConn, Context.BIND_AUTO_CREATE)
        }

        messenger_unbind.setOnClickListener {
            unbindService(messengerConn)
        }

        do_something.setOnClickListener {
            mLocalService?.doSomething()
            mMessenger?.send(Message.obtain())
            mRemoteService?.doSomething()

        }

    }

    private fun buildLocalIntent(): Intent {
        val remoteIntent = Intent()
        remoteIntent.action = "com.example.testapp.RemoteService"
        remoteIntent.`package` = "com.example.testapp"
        return remoteIntent
    }

    private fun buildMessengerIntent(): Intent {
        val remoteIntent = Intent()
        remoteIntent.action = "com.example.testapp.MessengerService"
        remoteIntent.`package` = "com.example.testapp"
        return remoteIntent
    }


    private fun buildRemoteIntent(): Intent {
        val remoteIntent = Intent()
        remoteIntent.action = "com.example.testapp.RemoteService"
        remoteIntent.`package` = "com.example.testapp"
        return remoteIntent
    }


    private val localConn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RemoteService.RemoteBinder
            mRemoteService = binder.service
        }
    }

    private val messengerConn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMessenger = Messenger(service)
        }
    }

    private val remoteConn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as RemoteService.RemoteBinder
            mRemoteService = binder.service
        }
    }

}
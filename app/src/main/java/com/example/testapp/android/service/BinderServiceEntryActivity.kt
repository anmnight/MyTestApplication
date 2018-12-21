package com.example.testapp.android.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_binder_service.*

class BinderServiceEntryActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_service)

        bindService(Intent(this, BinderService::class.java), conn, Context.BIND_AUTO_CREATE)

        send.setOnClickListener {

            val msg = Message.obtain()

            val bundle = Bundle()
            bundle.putString("msg", "message from client")

            msg.data = bundle
            msg.replyTo = mReplyMessenger

            mMessenger.send(msg)
        }

    }

    private lateinit var mMessenger: Messenger

    companion object {

        private val TAG = "BinderService"

        class ReplyHandler : Handler() {
            override fun handleMessage(msg: Message?) {
                val message = msg?.data?.getString("msg")


                Log.i(TAG, "Server : $message")


            }
        }
    }


    private val mReplyMessenger = Messenger(ReplyHandler())


    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }

    private val conn = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            mMessenger = Messenger(service)
        }

    }
}

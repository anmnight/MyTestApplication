package com.example.testapp.android.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_binder_service.*

class MessengerServiceEntryActivity : AppCompatActivity(), View.OnClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_binder_service)


        bind.setOnClickListener(this)
        unbind.setOnClickListener(this)


//        send.setOnClickListener {
//
//            val msg = Message.obtain()
//
//            val bundle = Bundle()
//            bundle.putString("msg", "message from client")
//
//            msg.data = bundle
//            msg.replyTo = mReplyMessenger
//
//            mMessenger.send(msg)
//        }

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


    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.bind -> {
                val intent = Intent()
                intent.action = "com.anmnight.remote.server"
                intent.setPackage("com.bankcomm.com.anmnight.remoteprocess")
                bindService(intent, conn, Context.BIND_AUTO_CREATE)
            }

            R.id.unbind -> unbindService(conn)
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

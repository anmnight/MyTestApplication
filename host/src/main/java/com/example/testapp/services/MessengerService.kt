package com.example.testapp.services

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message
import android.os.Messenger
import android.util.Log
import com.example.testapp.tag

class MessengerService : Service() {
      val tag = "MessengerService"

    override fun onBind(intent: Intent): IBinder {
        val messenger = Messenger(IncomingHandler())
        return messenger.binder
    }

    internal class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            Log.i(tag, "message: ${msg?.toString()}")
        }
    }


}

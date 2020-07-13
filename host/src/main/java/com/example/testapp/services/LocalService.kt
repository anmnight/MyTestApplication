package com.example.testapp.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class LocalService : Service() {

    private val tag = "LocalService"

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    fun doSomething() {
        Log.i(tag, "doSomething...")
    }
}

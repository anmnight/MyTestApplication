package com.example.testapp.andserver

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.anmnight.commlibrary.unit.HttpUnit
import com.example.testapp.andserver.manager.ServerHostBroadcastManager

import com.yanzhenjie.andserver.AndServer
import com.yanzhenjie.andserver.Server
import java.util.concurrent.TimeUnit

class ServerHost : Service() {


    private var mServer: Server? = null
    private val TAG = "ServerHost"


    override fun onCreate() {
        super.onCreate()

        if (mServer == null) {
            mServer = initServer()
        }

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        startServer()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        stopServer()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    private fun initServer(): Server {
        return AndServer.serverBuilder()
                .inetAddress(HttpUnit.getLocalIPAddress())
                .port(8088)
                .timeout(10, TimeUnit.SECONDS)
                .listener(ServerListener())
                .build()
    }

    private fun startServer() {

        if (!mServer!!.isRunning) {
            mServer?.startup()
        }


    }

    private fun stopServer() {
        if (mServer!!.isRunning) {
            mServer!!.shutdown()
        } else {
            Log.w("AndServer", "The server has not started yet.")
        }
    }

    inner class ServerListener : Server.ServerListener {

        override fun onStarted() {

            val address = "http://" + HttpUnit.getLocalIPAddress().hostAddress + ":8088"
            sendBroadcast(ServerHostBroadcastManager.startIntent(address))

        }

        override fun onStopped() {
            sendBroadcast(ServerHostBroadcastManager.stopIntent())
        }

        override fun onException(e: Exception) {
            e.printStackTrace()
        }
    }


}

package com.example.testapp.andserver

import android.content.Intent
import android.content.IntentFilter

object ServerHostBroadcast {

    const val start = "http.server.start"
    const val stop = "http.server.stop"
    const val address = "http.server.address"

    fun filter(): IntentFilter {
        val intentFilter = IntentFilter()
        intentFilter.addAction(start)
        intentFilter.addAction(address)

        return intentFilter
    }

    fun startIntent(ip: String): Intent {

        val intent = Intent()
        intent.action = start
        intent.putExtra(address, ip)

        return intent
    }

    fun stopIntent(): Intent {

        val intent = Intent()
        intent.action = stop
        return intent
    }

}

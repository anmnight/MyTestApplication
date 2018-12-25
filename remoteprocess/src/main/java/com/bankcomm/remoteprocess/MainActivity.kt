package com.bankcomm.remoteprocess

import android.Manifest
import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.view.View
import android.widget.Button


class MainActivity : Activity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.bind).setOnClickListener(this)
        findViewById<Button>(R.id.unbind).setOnClickListener(this)


        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivityForResult(intent, 1)
            } else {
                //TODO do something you need
            }
        }
    }

    override fun onClick(v: View) {

        when (v.id) {

            R.id.bind -> bindService(Intent(this, MessengerService::class.java), conn, Context.BIND_AUTO_CREATE)

            R.id.unbind -> unbindService(conn)
        }
    }

    private val conn = object : ServiceConnection {

        override fun onServiceDisconnected(name: ComponentName?) {

        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {

        }

    }


}

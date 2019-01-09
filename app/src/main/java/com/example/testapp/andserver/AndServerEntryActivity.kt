package com.example.testapp.andserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.anmnight.commlibrary.widget.LoadingTextDialog
import com.example.testapp.R
import com.example.testapp.andserver.manager.ServerHostBroadcastManager
import com.example.testapp.andserver.manager.WifiUtils
import kotlinx.android.synthetic.main.activity_and_server_entry.*

class AndServerEntryActivity : AppCompatActivity(), View.OnClickListener {


    private val TAG = "AndServerEntryActivity"

    private lateinit var mServiceIntent: Intent

    private lateinit var mReceiver: ServerHostReceiver

    private lateinit var mWifiManager: WifiManager

    private lateinit var mDialog: LoadingTextDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_and_server_entry)

        mServiceIntent = Intent(this, ServerHost::class.java)

        mWifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        mReceiver = ServerHostReceiver()



        submit.setOnClickListener(this)

        registerReceiver(mReceiver, ServerHostBroadcastManager.filter())


    }


    override fun onClick(v: View?) {

//        when (v?.id) {
//
//            R.id.start_server -> startService(mServiceIntent)
//
//            R.id.stop_server -> stopService(mServiceIntent)
//
//        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(mReceiver)
        stopService(mServiceIntent)
    }

    inner class ServerHostReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {

            val action = intent.action

            when (action) {

                ServerHostBroadcastManager.start -> {
                    WifiUtils.createHotspot(mWifiManager)
                    val address = intent.getStringExtra(ServerHostBroadcastManager.address)


                }

                ServerHostBroadcastManager.stop -> {

                    WifiUtils.closeWifiHotspot(mWifiManager)
                }


            }

        }
    }


}

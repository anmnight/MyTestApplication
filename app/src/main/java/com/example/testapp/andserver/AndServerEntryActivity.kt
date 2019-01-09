package com.example.testapp.andserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_and_server_entry.*

class AndServerEntryActivity : AppCompatActivity(), View.OnClickListener {


    private val TAG = "AndServerEntryActivity"

    private lateinit var mServiceIntent: Intent

    private lateinit var mReceiver: ServerHostReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_and_server_entry)

        mServiceIntent = Intent(this, ServerHost::class.java)

        start_server.setOnClickListener(this)

        stop_server.setOnClickListener(this)


        mReceiver = ServerHostReceiver()

        registerReceiver(mReceiver, ServerHostBroadcast.filter())


    }


    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.start_server -> startService(mServiceIntent)

            R.id.stop_server -> stopService(mServiceIntent)

        }

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

                ServerHostBroadcast.start -> {
                    val address = intent.getStringExtra(ServerHostBroadcast.address)
                    editText.setText(address)
                }

                ServerHostBroadcast.stop -> {
                    editText.setText(ServerHostBroadcast.stop)
                }


            }

        }
    }


}

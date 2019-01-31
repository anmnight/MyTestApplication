package com.example.testapp.android.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.os.IBinder
import android.view.View
import com.anmnight.remoteprocess.ISystemLogInterface

import com.example.testapp.R
import kotlinx.android.synthetic.main.content_service.*

class WorkManagerEntryActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_manager_entry)


        bind_service.setOnClickListener(this)
        unbind_service.setOnClickListener(this)

        start_service.setOnClickListener(this)
        stop_service.setOnClickListener(this)


    }

    private var mSystemLogService: ISystemLogInterface? = null


    private val conn = object : ServiceConnection {


        override fun onServiceDisconnected(p0: ComponentName?) {
            mSystemLogService = null
        }

        override fun onServiceConnected(p0: ComponentName?, p1: IBinder?) {
            mSystemLogService = ISystemLogInterface.Stub.asInterface(p1)
        }
    }


    override fun onClick(p0: View?) {

        when (p0?.id) {

            R.id.bind_service -> {
                val intent = Intent()
                intent.setPackage("com.anmnight.remoteprocess")
                intent.action = "com.anmnight.remote.system.log"
                bindService(intent, conn, Context.BIND_AUTO_CREATE)
            }

            R.id.unbind_service -> unbindService(conn)

            R.id.start_service -> mSystemLogService?.startSystemLog()

            R.id.stop_service -> mSystemLogService?.stopSystemLog()

        }

    }
}

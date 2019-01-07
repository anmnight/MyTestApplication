package com.example.testapp.andserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.testapp.R
import com.example.testapp.unit.HttpUnit
import kotlinx.android.synthetic.main.activity_and_server_entry.*
import java.net.InetAddress
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

class AndServerEntryActivity : AppCompatActivity(), View.OnClickListener {


    private val TAG = "AndServerEntryActivity"

    private val executor = Executors.newSingleThreadExecutor()

    private val mHostFuture = FutureTask(HostInitRunnable())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_and_server_entry)

        start_server.setOnClickListener(this)

        stop_server.setOnClickListener(this)

        executor.submit(mHostFuture)

    }


    class HostInitRunnable : Callable<ServerHost> {
        override fun call(): ServerHost {
            return ServerHost(HttpUnit.getLocalIPAddress())
        }
    }


    override fun onClick(v: View?) {

        when (v?.id) {

            R.id.start_server -> mHostFuture.get().startServer()

            R.id.stop_server -> mHostFuture.get().stopServer()

        }

    }
}

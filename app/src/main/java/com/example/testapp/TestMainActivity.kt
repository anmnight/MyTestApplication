package com.example.testapp

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.anmnight.home.ITestInterface
import com.bankcomm.commlibrary.task.WorkFactory
import java.lang.Thread.sleep

class TestMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TestHomeApplication.application.mViewAdapter.setAutoViewSize(this)

        TestHomeApplication.application.mHeader.setActivityBound(this)

        setContentView(R.layout.activity_test_main)




        WorkFactory.doSynWork()

                .doThisOnMain(Runnable {
                    Log.i("TestMainActivity","im main")
                })
                .doThisOnWork(Runnable {
                    sleep(10000)
                    Log.i("TestMainActivity","im work")
                })
                .doThisOnMain(Runnable {
                    Log.i("TestMainActivity","im main")
                })
                .go()


    }

    override fun onDestroy() {
        super.onDestroy()
        unbindService(conn)
    }

    private var mRemoteService: ITestInterface? = null

    private val conn = object : ServiceConnection {
        /**
         * Called when a connection to the Service has been lost.  This typically
         * happens when the process hosting the service has crashed or been killed.
         * This does *not* remove the ServiceConnection itself -- this
         * binding to the service will remain active, and you will receive a call
         * to [.onServiceConnected] when the Service is next running.
         *
         * @param name The concrete component name of the service whose
         * connection has been lost.
         */
        override fun onServiceDisconnected(name: ComponentName?) {
            mRemoteService = null
        }

        /**
         * Called when a connection to the Service has been established, with
         * the [android.os.IBinder] of the communication channel to the
         * Service.
         *
         * @param name The concrete component name of the service that has
         * been connected.
         *
         * @param service The IBinder of the Service's communication channel,
         * which you can now make calls on.
         */
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {


            mRemoteService = ITestInterface.Stub.asInterface(service)

        }

    }
}

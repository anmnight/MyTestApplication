package com.example.testapp.android

import android.os.*
import android.support.v7.app.AppCompatActivity
import com.example.testapp.mytestapplication.R
import unit.Logger

class ThreadHandler : AppCompatActivity() {

    lateinit var mHandler1: Handler
    lateinit var mHandler2: Handler
    lateinit var mHandlerThread1: HandlerThread
    lateinit var mHandlerThread2: HandlerThread

    /**
     * 两个线程 mHandlerThread1 mHandlerThread2 相互发送消息
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_handler)

        mHandlerThread1 = HandlerThread("mHandlerThread1")
        mHandlerThread1.start()

        mHandlerThread2 = HandlerThread("mHandlerThread2")
        mHandlerThread2.start()

        mHandler1 = object : Handler(mHandlerThread1.looper) {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                Logger.debug("mHandlerThread1 got a message")
                Logger.debug(Thread.currentThread().name + "is sleeping...")
                Thread.sleep(3000)
                Logger.debug(Thread.currentThread().name + "is sleeping finish")
                sendTomHandlerThread(mHandler1, mHandler2)
            }
        }
        mHandler2 = object : Handler(mHandlerThread2.looper) {
            override fun handleMessage(msg: Message?) {
                super.handleMessage(msg)
                Logger.debug("mHandlerThread2 got a message")
                Logger.debug(Thread.currentThread().name + "is sleeping...")
                Thread.sleep(3000)
                Logger.debug(Thread.currentThread().name + "is sleeping finish")
                sendTomHandlerThread(mHandler2, mHandler1)
            }
        }

        mHandler1.sendEmptyMessage(0)
    }

    fun sendTomHandlerThread(sendhandler: Handler, receivehandler: Handler) {
        sendhandler.post({ receivehandler.sendEmptyMessage(0) })
    }

    override fun onPause() {
        super.onPause()
        mHandlerThread1.interrupt()
        mHandlerThread2.interrupt()
    }


}

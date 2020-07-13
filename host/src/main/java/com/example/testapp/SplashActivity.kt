package com.example.testapp

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val tag = "SplashActivity"
    private val handlerThread = HandlerThread("worker")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        handlerThread.start()

        val handlerB = Handler(handlerThread.looper)


        button.setOnClickListener {



        }
    }


    class CusThread : Thread() {
        override fun run() {
            super.run()

            while (true) {
                sleep(800)
                Log.i(tag, "do something...")
            }
        }
    }

}

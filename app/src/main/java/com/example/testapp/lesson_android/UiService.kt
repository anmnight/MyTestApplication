package com.example.testapp.lesson_android

import android.app.IntentService
import android.app.Service
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.WindowManager
import com.example.testapp.app.HomeApplication
import com.example.testapp.mytestapplication.R
import android.view.Gravity
import android.graphics.PixelFormat
import android.os.*
import android.view.ViewGroup
import android.widget.TextView
import com.example.testapp.app.Logger
import java.lang.Thread.sleep


/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/2 14:48
 * anmnight@qq.com
 */
class UiService : IntentService("test") {

    companion object {
        private const val action_show_dialog = "action.show.dialog"

        fun showDialog(context: Context) {
            val intent = Intent(context, UiService::class.java)
            intent.action = action_show_dialog
            context.startService(intent)
        }
    }

    var mWindowManager = HomeApplication.application.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    private val mView = LayoutInflater.from(HomeApplication.application).inflate(R.layout.dialog_server_dialog, null)
    private val mParams = WindowManager.LayoutParams()

    init {
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT
        val flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        mParams.flags = flags
        mParams.format = PixelFormat.TRANSLUCENT
        mParams.width = ViewGroup.LayoutParams.MATCH_PARENT
        mParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        mParams.gravity = Gravity.CENTER
    }

    override fun onHandleIntent(p0: Intent?) {
        Logger.info("onHandleIntent")

        while (true) {
            sleep(1000)
            handler.post(runnable)
        }
    }

    override fun onCreate() {
        super.onCreate()
        Logger.info("onCreate")

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Logger.info("onStartCommand")
        addDialog()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Logger.info("onUnbind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Logger.info("onDestroy")
    }


    var flag = 0
    val runnable = Runnable {

        val textView = mView.findViewById<TextView>(R.id.window_dialog)
        textView.text = "Flag : $flag"
        mView.requestLayout()
        flag += 1

    }

    val handler = Handler()

    fun addDialog() {

        mWindowManager.addView(mView, mParams)

    }

    fun hideDialog() {
        mWindowManager.removeView(mView)
    }


    override fun onBind(p0: Intent?): IBinder {
        return UiBinder()
    }


    inner class UiBinder : Binder() {
        fun getService(): UiService {
            return this@UiService
        }
    }

}
package com.example.testapp.android.service

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.IBinder
import com.example.testapp.R
import com.example.testapp.TestHomeApplication
import com.example.testapp.TestMainActivity

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/6 14:32
 * anmnight@qq.com
 */
class TestService : Service() {


    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        buildNotification()


        return super.onStartCommand(intent, flags, startId)
    }





    private fun buildNotification() {

        val notificationBuilder = Notification.Builder(TestHomeApplication.application)

        val nfIntent = Intent(this, TestMainActivity::class.java)

        notificationBuilder.setContentIntent(PendingIntent.getActivity(this, 0, nfIntent, 0))
                .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.mipmap.ic_launcher))
                .setContentTitle("Title")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentText("ContentText")
                .setWhen(System.currentTimeMillis())

        val notification = notificationBuilder.build()
        notification.defaults = Notification.DEFAULT_SOUND
        startForeground(101, notification)
    }

}
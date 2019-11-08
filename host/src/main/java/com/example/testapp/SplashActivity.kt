package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.testapp.activities.android.BroadcastActivity
import com.example.testapp.activities.android.LinearLayoutActivity
import com.example.testapp.activities.android.PopupWindowActivity
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread {
            sleep(3000)
            runOnUiThread {
                startActivity(Intent(this, PopupWindowActivity::class.java))
            }
        }.start()


    }


}

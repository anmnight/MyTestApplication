package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.activities.android.LinearLayoutActivity
import java.lang.Thread.sleep

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread {
            sleep(3000)
            runOnUiThread {

                startActivity(Intent(this, LinearLayoutActivity::class.java))
                overridePendingTransition(R.anim.bottom_from_top, R.anim.top_from_bottom)
                finish()

            }
        }.start()


    }
}

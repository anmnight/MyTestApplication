package com.example.testapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.android.viewpager.ViewPagerAdapter
import com.example.testapp.mytestapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)






        button.setOnClickListener {
            startActivity(Intent(this@MainActivity, ViewPagerAdapter::class.java))
        }


    }


}

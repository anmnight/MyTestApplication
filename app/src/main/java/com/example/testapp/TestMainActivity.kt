package com.example.testapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bankcomm.commlibrary.logger.LogFactory
import com.bankcomm.commlibrary.logger.Logger

class TestMainActivity : AppCompatActivity() {


    private lateinit var log: Logger


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        TestHomeApplication.application.mViewAdapter.setAutoViewSize(this)

        setContentView(R.layout.activity_test_main)
        log = LogFactory(application)

    }
}

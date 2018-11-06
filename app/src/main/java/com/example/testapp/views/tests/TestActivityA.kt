package com.example.testapp.views.tests

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.testapp.R
import com.example.testapp.TestHomeApplication

class TestActivityA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TestHomeApplication.application.mViewAdapter.setAutoViewSize(this)
        setContentView(R.layout.activity_test_a)

        startActivity(Intent(this, TestActivityB::class.java))
    }
}

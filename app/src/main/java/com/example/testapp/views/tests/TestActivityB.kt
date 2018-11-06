package com.example.testapp.views.tests

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import com.example.testapp.R
import kotlinx.android.synthetic.main.activity_test_b.*

class TestActivityB : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_b)

        button.setOnClickListener {
            finish()
        }
    }
}

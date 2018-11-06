package com.example.testapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.testapp.views.tests.TestActivityA
import kotlinx.android.synthetic.main.activity_test_main.*

class TestMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_main)


        button.setOnClickListener {
            startActivity(Intent(this, TestActivityA::class.java))
        }

    }

}

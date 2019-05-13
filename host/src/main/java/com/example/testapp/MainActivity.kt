package com.example.testapp

import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_test_main.*

class MainActivity : BaseActivity() {

    private val MAIN_ENTERY_NAME = "Entry"
    private val tag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_test_main)


        text.scrollTo(0, 100)

        Log.i(tag, "im child");


    }


}

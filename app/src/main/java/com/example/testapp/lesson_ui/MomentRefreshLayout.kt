package com.example.testapp.lesson_ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import com.example.testapp.mytestapplication.R
import kotlinx.android.synthetic.main.activity_moment_refresh_layout.*

class MomentRefreshLayout : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moment_refresh_layout)

        val list = ArrayList<String>()

        var i = 0
        while (i < 20) {
            list.add("index : $i")
            i++
        }

        val layoutManager = LinearLayoutManager(this)
        val adapter = MomentAdapter(this, list)
        listview.layoutManager = layoutManager
        listview.adapter = adapter


    }


}

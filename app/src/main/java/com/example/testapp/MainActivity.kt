package com.example.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.app.Logger
import com.example.testapp.homeinns.rooms.di.DaggerDataRepoComponent


import com.example.testapp.mytestapplication.R
import unit.AppStorage
import javax.inject.Inject


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




    }


}

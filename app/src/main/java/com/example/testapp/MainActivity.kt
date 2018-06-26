package com.example.testapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.app.Logger
import com.example.testapp.homeinns.rooms.di.DaggerDataRepoComponent


import com.example.testapp.mytestapplication.R
import unit.AppStorage
import javax.inject.Inject


class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var storage: AppStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        DaggerDataRepoComponent.create().subDataRepoComponent().inject(this)

//        DaggerSubDataRepoComponent.builder().dataRepoComponent(dataRepoComponent).build().inject(this)

        storage.saveToken("asdasda")

        Logger.info("MainActivity Token : ${storage.getToken()}")


    }


}

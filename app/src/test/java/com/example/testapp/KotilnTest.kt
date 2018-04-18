package com.example.testapp

import android.app.Activity
import android.os.Bundle
import java.sql.DriverManager


class a : Activity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun sum(a:Int,b:Int):Int{
        DriverManager.println("test $a sub $b \\$a")
        return a+b
    }

}


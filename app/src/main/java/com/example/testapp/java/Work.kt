package com.example.testapp.java

import java.util.*

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/9/20 17:23
 * anmnight@qq.com
 */
class Work : Observable() {

    fun doWork(){
        setChanged()
        notifyObservers()
    }

}
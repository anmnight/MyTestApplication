package com.example.testapp.dagger2

import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/10/10 09:44
 * anmnight@qq.com
 */

class Heater  {

    @Inject
    constructor()

    fun heating(){
        System.out.println("heating")
    }
}
package com.example.testapp.dagger2

import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/10/10 09:45
 * anmnight@qq.com
 */
class CoffeeMaker {

    @Inject
    lateinit var thermosiphon:Thermosiphon

    fun make(){
        thermosiphon.pumping()
    }


}
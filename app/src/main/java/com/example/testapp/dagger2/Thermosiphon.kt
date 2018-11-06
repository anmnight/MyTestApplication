package com.example.testapp.dagger2

import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/10/10 09:43
 * anmnight@qq.com
 */
class Thermosiphon  :Pump {


    private lateinit var mHeater:Heater

    @Inject constructor(heater: Heater){
        this.mHeater = heater
    }

    override fun pumping() {
        mHeater.heating()
    }
}
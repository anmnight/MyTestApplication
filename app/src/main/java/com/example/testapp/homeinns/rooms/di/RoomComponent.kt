package com.example.testapp.homeinns.rooms.di

import com.example.testapp.homeinns.rooms.RoomsActivity
import dagger.Component

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/31 19:07
 * anmnight@qq.com
 */


@Component(modules = [(RoomModule::class)])
interface RoomComponent {
    fun inject(activity: RoomsActivity)
}
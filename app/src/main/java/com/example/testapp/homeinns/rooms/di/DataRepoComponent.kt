package com.example.testapp.homeinns.rooms.di

import com.example.testapp.homeinns.rooms.data.RoomModelImpl
import dagger.Component

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/1 11:04
 * anmnight@qq.com
 */
@Component(modules = [(DataRepoModule::class)])
interface DataRepoComponent {
    fun inject(model: RoomModelImpl)

}
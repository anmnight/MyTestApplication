package com.example.testapp.homeinns.rooms.di

import dagger.Component
import unit.AppStorage

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/1 11:04
 * anmnight@qq.com
 */
@Component(modules = [(DataRepoModule::class)])
interface DataRepoComponent {

    fun subDataRepoComponent():SubDataRepoComponent

}
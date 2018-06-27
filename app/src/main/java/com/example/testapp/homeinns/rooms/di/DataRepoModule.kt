package com.example.testapp.homeinns.rooms.di

import com.example.testapp.app.Logger
import dagger.Module
import dagger.Provides
import unit.AppExecutor
import unit.AppStorage

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/1 11:05
 * anmnight@qq.com
 */

@Module
class DataRepoModule(crashSize: Int) {

    init {
        Logger.info("crash size : $crashSize")
    }


    @Provides
    fun provideAppExecutor(
            diskExe: AppExecutor.Companion.DiskIOExecutor,
            netExe: AppExecutor.Companion.NetworkIOExecutor,
            mainExe: AppExecutor.Companion.MainThreadExecutor): AppExecutor = AppExecutor(diskExe, netExe, mainExe)

    @Provides
    fun provideAppStorage(): AppStorage = AppStorage()
}
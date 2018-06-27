package com.example.testapp.homeinns.rooms.di

import com.example.testapp.homeinns.rooms.RoomContract
import com.example.testapp.homeinns.rooms.data.RoomModel
import dagger.Module
import dagger.Provides

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/27 09:39
 * anmnight@qq.com
 */


@Module
class RoomModule(view: RoomContract.RoomView, model: RoomModel) {

    private var mView: RoomContract.RoomView = view
    private var mModel: RoomModel = model

    @Provides
    fun provideRoomView(): RoomContract.RoomView = mView

    @Provides
    fun provideRoomModel(): RoomModel = mModel

}
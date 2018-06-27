package com.example.testapp.homeinns.rooms.impl

import com.example.testapp.homeinns.rooms.RoomContract
import com.example.testapp.homeinns.rooms.data.RoomModel
import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 17:18
 * anmnight@qq.com
 */
class RoomPresentImpl @Inject constructor(view: RoomContract.RoomView, model: RoomModel) {

    private var mView: RoomContract.RoomView = view
    private var mModel: RoomModel = model

    init {
        checkNotNull(view)
        checkNotNull(model)
    }

    fun login() {

        mView.showToast("Im Dagger")

    }

    fun loadRoom() {

    }
}
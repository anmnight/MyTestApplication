package com.example.testapp.homeinns.rooms.impl

import android.os.Looper
import com.example.testapp.app.Logger
import com.example.testapp.homeinns.rooms.RoomContract
import com.example.testapp.homeinns.rooms.data.RoomModel
import com.example.testapp.homeinns.rooms.pojo.LoginBean

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 17:18
 * anmnight@qq.com
 */
open class RoomPresentImpl(model: RoomModel, view: RoomContract.RoomView) : RoomContract.RoomPresenter {

    private var mView: RoomContract.RoomView = view
    private var mModel: RoomModel = model


    override fun login() {
        mView.showLoading()
        val lb = LoginBean("18502938991", "96e79218965eb72c92a549dd5a330112")
        mModel.login(lb, RoomLoginResultImpl())
    }

    override fun loadRoom() {
//        val rooms = mModel.loadRooms()
//
//        Logger.err(rooms)
    }

    init {
        checkNotNull(model)
        checkNotNull(view)
    }


    class RoomLoginResultImpl : RoomModel.RoomLoginResult {
        override fun onLoginSuccess() {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun error(err: String?) {

        }

    }

}
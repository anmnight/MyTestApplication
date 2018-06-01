package com.example.testapp.homeinns.rooms.impl

import android.os.Handler
import android.os.HandlerThread
import com.example.testapp.app.Logger
import com.example.testapp.homeinns.rooms.RoomContract
import com.example.testapp.homeinns.rooms.data.RoomModel
import com.example.testapp.homeinns.rooms.pojo.LoginBean

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 17:18
 * anmnight@qq.com
 */
class RoomPresentImpl(model: RoomModel, view: RoomContract.RoomView) : RoomContract.RoomPresenter {

    private var mView: RoomContract.RoomView = view
    private var mModel: RoomModel = model
    private val workThread = HandlerThread("worker")
    private val mHandler: Handler

    init {
        workThread.start()
        mHandler = Handler(workThread.looper)
    }

    override fun login() {

        mView.showLoading()
        mHandler.post {
            val lb = LoginBean("18502938991", "96e79218965eb72c92a549dd5a330112")
            mModel.login(lb)
            mView.hideLoading()
        }


    }

    override fun loadRoom() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    init {
        checkNotNull(model)
        checkNotNull(view)
    }
}
package com.example.testapp.homeinns.rooms.data

import com.example.testapp.homeinns.rooms.data.remote.RoomApi
import com.example.testapp.homeinns.rooms.pojo.LoginBean
import unit.StoreUtil
import javax.inject.Singleton

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 16:23
 * anmnight@qq.com
 */
@Singleton
class RoomModelImpl(private var store: StoreUtil, private var callback: RequestCallback) : RoomModel {

    interface RequestCallback {
        fun onNetEr(message: String)
    }

    override fun login(login: LoginBean) {

        val response = store.request(RoomApi::class.java).login(login).execute()

        if (response.code() == 200) {

            if (response.body()?.authToken != null) {
                store.saveToken(response.body()!!.authToken)
            } else {
                callback.onNetEr("请求异常")
            }

        } else {
            callback.onNetEr("登陆失败")
        }
    }

    override fun loadRooms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
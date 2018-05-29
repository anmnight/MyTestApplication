package com.example.testapp.homeinns.rooms.impl

import com.example.testapp.app.Logger
import com.example.testapp.homeinns.rooms.RoomModel
import com.example.testapp.homeinns.rooms.data.remote.RoomApi
import com.example.testapp.homeinns.rooms.pojo.LoginBean
import http.RestClient
import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 16:23
 * anmnight@qq.com
 */
class RoomModelImpl : RoomModel {

    @Inject
    constructor()

    @Inject
    lateinit var rest: RestClient

    override fun login(login: LoginBean?) {

        val lb = LoginBean("18502938991","96e79218965eb72c92a549dd5a330112")

        val resq = rest.request(RoomApi::class.java).login(lb).execute()

        Logger.info("new test : $resq")
    }

    override fun loadRooms() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
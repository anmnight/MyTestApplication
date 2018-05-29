package com.example.testapp.homeinns.rooms.impl

import com.example.testapp.homeinns.rooms.RoomModel
import com.example.testapp.homeinns.rooms.RoomPresent
import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 17:18
 * anmnight@qq.com
 */
class RoomPresentImpl :RoomPresent{

    @Inject
    lateinit var model: RoomModel

    override fun getRooms() {
        model.login(null)
    }

    override fun update() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
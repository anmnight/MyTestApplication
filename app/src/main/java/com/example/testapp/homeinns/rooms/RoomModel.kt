package com.example.testapp.homeinns.rooms

import com.example.testapp.homeinns.rooms.pojo.LoginBean

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 16:15
 * anmnight@qq.com
 */
interface RoomModel {
    fun login(login: LoginBean?)
    fun loadRooms()
}
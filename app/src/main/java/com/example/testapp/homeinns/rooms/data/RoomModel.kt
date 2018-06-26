package com.example.testapp.homeinns.rooms.data

import com.example.testapp.homeinns.rooms.pojo.LoginBean
import com.example.testapp.homeinns.rooms.pojo.RoomTypesBean
import com.example.testapp.homeinns.rooms.pojo.UserBean

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 16:15
 * anmnight@qq.com
 */
interface RoomModel {
    fun login(login: LoginBean,result:RoomLoginResult)
    fun loadRooms(result: LoadRoomResult)

    interface RoomLoginResult {
        fun onLoginSuccess()
        fun error(err: String?)
    }

    interface LoadRoomResult {
        fun onLoadSuccess(types: RoomTypesBean)
        fun error(err: String?)
    }


}
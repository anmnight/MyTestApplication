package com.example.testapp.homeinns.rooms

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/31 16:56
 * anmnight@qq.com
 */
interface RoomContract {

    interface RoomView {
        fun showLoading()
        fun hideLoading()
        fun showToast(message:String)
        fun updateRoom()
        fun setRooms()
    }

    interface RoomPresenter {
        fun login()
        fun loadRoom()
    }

}
package com.anmnight.remoteprocess.model

interface RoomRepository{

    fun loadRoomsOrder()

    fun loadRoomInfo()

    fun updateRoomsOrder()

    fun updateRoomsInfo()
}

package com.anmnight.remoteprocess.model.remote


import com.anmnight.commlibrary.http.Call
import com.anmnight.commlibrary.http.core.Get
import com.anmnight.commlibrary.http.core.Header
import com.anmnight.commlibrary.http.core.Query
import com.anmnight.remoteprocess.pojo.HotelRoomsInfo
import com.anmnight.remoteprocess.pojo.RoomInfo

interface RemoteApi {

    @Get("api/pms/data?url=api/v1/HotelRoom/GetQueryHotelRoomListJSON/X00105")
    fun rooms(@Header("Authorization") headToken: String): Call<HotelRoomsInfo>

    @Get("api/bizroom/GetRoomStatus?hotelId=X00105&pageSize=8")
    fun orders(
            @Header("Authorization") headToken: String,
            @Query("date") today: String,
            @Query("pageIndex") index: Int
    ): Call<List<RoomInfo.SimpleOrder>>
}

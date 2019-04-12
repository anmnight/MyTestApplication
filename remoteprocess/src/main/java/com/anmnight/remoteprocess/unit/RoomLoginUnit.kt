package com.anmnight.remoteprocess.unit

import android.content.Context
import android.util.Log
import com.anmnight.remoteprocess.pojo.RoomTypeInfo

class RoomLoginUnit {

    private val tag = "RoomLoginUnit"


    fun login(mContex: Context) {


        val ins = mContex.assets.open("TestRoomsData.json")

        val roomStr = String(ins.readBytes())





//        val rooms:List<RoomTypeInfo> =
//
//        Log.d(tag, "Rooms : $roomStr")


    }
}

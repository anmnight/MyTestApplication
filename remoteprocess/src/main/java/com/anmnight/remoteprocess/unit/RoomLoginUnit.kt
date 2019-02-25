package com.anmnight.remoteprocess.unit

import android.util.Log
import com.anmnight.remoteprocess.RemoteApplication
import com.anmnight.remoteprocess.pojo.PostLoginBean

class RoomLoginUnit {

    private val tag = "RoomLoginUnit"

    private val mApi = RemoteApplication.mRoomRest.create(LoginApi::class.java)

    fun login() {

        val lb = PostLoginBean("18502938991", "96e79218965eb72c92a549dd5a330112")

        val userResponseBean = mApi.login(lb).execute().body()



        Log.i(tag, ">>>>>>>>>>>>>>>>>>finish")
        Log.i(tag, "${userResponseBean?.authToken}}")
        Log.i(tag, "${userResponseBean?.userName}}")
        Log.i(tag, "${userResponseBean?.friendlyName}}")

    }
}

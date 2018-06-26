package com.example.testapp.homeinns.rooms.data

import com.example.testapp.app.Logger
import com.example.testapp.homeinns.rooms.data.remote.RoomApi
import com.example.testapp.homeinns.rooms.di.DaggerDataRepoComponent
import com.example.testapp.homeinns.rooms.pojo.LoginBean
import com.example.testapp.homeinns.rooms.pojo.RoomTypesBean
import com.example.testapp.homeinns.rooms.pojo.UserBean
import unit.AppExecutor
import unit.AppStorage
import unit.BaseView
import java.util.concurrent.Callable
import javax.inject.Inject

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/29 16:23
 * anmnight@qq.com
 */
class RoomModelImpl : RoomModel {

    @Inject
    lateinit var executor: AppExecutor

    @Inject
    lateinit var store: AppStorage


    init {
//        DaggerDataRepoComponent.builder().build().inject(this)
    }


    override fun login(login: LoginBean, result: RoomModel.RoomLoginResult) {


        executor.networkIO().execute {
            try {
                val user = store.request(RoomApi::class.java).login(login).execute().body()
                store.saveToken(user!!.authToken)
                executor.mainThread().execute {
                    result.onLoginSuccess()
                }

            } catch (e: Exception) {
                executor.mainThread().execute {
                    result.error(e.message)
                }
            }
        }
        
    }

    override fun loadRooms(result: RoomModel.LoadRoomResult) {

    }
}
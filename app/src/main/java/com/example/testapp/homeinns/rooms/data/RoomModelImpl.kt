package com.example.testapp.homeinns.rooms.data

import com.example.testapp.homeinns.rooms.data.remote.RoomApi
import com.example.testapp.homeinns.rooms.di.DaggerDataRepoComponent
import com.example.testapp.homeinns.rooms.pojo.LoginBean
import com.example.testapp.homeinns.rooms.pojo.RoomTypesBean
import com.example.testapp.homeinns.rooms.pojo.UserBean
import unit.AppExecutor
import unit.AppStorage
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
        DaggerDataRepoComponent.builder().build().inject(this)
    }

    override fun login(login: LoginBean): UserBean {

        return executor
                .run(
                        executor.networkIO(),
                        Callable<UserBean> {
                            return@Callable store.request(RoomApi::class.java).login(login).execute().body()
                        }
                )
    }

    override fun saveToken(token: String) {
        store.saveToken(token)
    }

    override fun getToken(): String = store.getToken()

    override fun loadRooms(): RoomTypesBean {
        return executor.run(executor.networkIO(), Callable<RoomTypesBean> {
            return@Callable store.request(RoomApi::class.java).roomType(store.getToken()).execute().body()
        })
    }
}
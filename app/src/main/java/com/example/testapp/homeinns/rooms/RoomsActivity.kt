package com.example.testapp.homeinns.rooms

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.app.Logger
import com.example.testapp.app.ToastUnit
import com.example.testapp.homeinns.rooms.data.RoomModelImpl
import com.example.testapp.homeinns.rooms.impl.RoomPresentImpl
import com.example.testapp.mytestapplication.R
import unit.StoreUtil
import javax.inject.Inject


class RoomsActivity : AppCompatActivity(), RoomContract.RoomView, RoomModelImpl.RequestCallback {

    lateinit var mPresent: RoomContract.RoomPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        mPresent = RoomPresentImpl(RoomModelImpl(StoreUtil(), this), this)


        mPresent.login()


    }


    override fun showLoading() {
        Logger.info("loading..")
    }

    override fun hideLoading() {
        Logger.info("hide loading")
    }

    override fun showToast() {
        ToastUnit.sortToase("Toast")
    }

    override fun updateRoom() {

    }

    override fun setRooms() {

    }

    override fun onNetEr(message: String) {
        ToastUnit.sortToase(message)
    }


}

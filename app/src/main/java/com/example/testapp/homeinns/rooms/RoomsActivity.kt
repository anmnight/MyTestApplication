package com.example.testapp.homeinns.rooms

import android.app.ProgressDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.app.ToastUnit
import com.example.testapp.homeinns.rooms.data.RoomModelImpl
import com.example.testapp.homeinns.rooms.di.DaggerRoomComponent
import com.example.testapp.homeinns.rooms.di.RoomModule
import com.example.testapp.homeinns.rooms.impl.RoomPresentImpl
import com.example.testapp.mytestapplication.R
import javax.inject.Inject

class RoomsActivity : AppCompatActivity(), RoomContract.RoomView {


    @Inject
    lateinit var mPresent:RoomPresentImpl

    private lateinit var dialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        DaggerRoomComponent.builder().roomModule(RoomModule(this,RoomModelImpl())).build().inject(this)


        mPresent.login()

    }


    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.hide()
    }

    override fun showToast(message: String) {
        ToastUnit.sortToase(message)
    }

    override fun updateRoom() {

    }

    override fun setRooms() {

    }

}

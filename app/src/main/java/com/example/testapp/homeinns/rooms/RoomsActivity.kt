package com.example.testapp.homeinns.rooms

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.app.Logger
import com.example.testapp.app.ToastUnit
import com.example.testapp.homeinns.rooms.data.RoomModelImpl
import com.example.testapp.homeinns.rooms.di.DaggerDataRepoComponent
import com.example.testapp.homeinns.rooms.impl.RoomPresentImpl
import com.example.testapp.mytestapplication.R
import unit.AppStorage
import javax.inject.Inject

class RoomsActivity : AppCompatActivity(), RoomContract.RoomView {

    lateinit var mPresent: RoomContract.RoomPresenter

    private lateinit var dialog: ProgressDialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)




        dialog = ProgressDialog(this@RoomsActivity)

        mPresent = RoomPresentImpl(RoomModelImpl(), this)

        mPresent.login()


    }


    override fun showLoading() {
        dialog.show()
    }

    override fun hideLoading() {
        dialog.hide()
    }

    override fun showToast() {
        ToastUnit.sortToase("Toast")
    }

    override fun updateRoom() {

    }

    override fun setRooms() {

    }

}

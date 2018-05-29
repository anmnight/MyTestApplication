package com.example.testapp.homeinns.rooms

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.testapp.homeinns.rooms.impl.RoomPresentImpl
import com.example.testapp.mytestapplication.R

class RoomsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rooms)

        val p = RoomPresentImpl()

        p.getRooms()



    }
}

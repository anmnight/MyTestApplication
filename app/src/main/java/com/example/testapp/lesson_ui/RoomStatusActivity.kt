package com.example.testapp.lesson_ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.testapp.lesson_ui.roomstatus.OberverScrollView
import com.example.testapp.mytestapplication.R
import com.example.testapp.app.ToastUnit
import com.example.testapp.app.WaitingDialog
import com.example.testapp.lesson_ui.roomstatus.NextLineLayoutManger
import com.example.testapp.lesson_ui.roomstatus.RoomListAdapter
import com.example.testapp.lesson_ui.roomstatus.RoomStatusListModel
import com.example.testapp.lesson_ui.roomstatus.RoomStatusModel
import java.text.SimpleDateFormat
import java.util.ArrayList
import java.util.Date
import kotlinx.android.synthetic.main.activity_room_status.*

class RoomStatusActivity : AppCompatActivity() {


    private val HTTPTHREAD = "http_thread"
    private var mDoHttpHandler: Handler? = null
    lateinit var mAdapter: RoomListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_status)


        val layoutManager = NextLineLayoutManger()
        layoutManager.isAutoMeasureEnabled = true
        mAdapter = RoomListAdapter(this)

        room_list.layoutManager = layoutManager
        room_list.adapter = mAdapter

        val httpThread = HandlerThread(HTTPTHREAD)
        httpThread.start()
        mDoHttpHandler = Handler(httpThread.looper)

        loadRoomStatus()

        val adapter = ArrayAdapter<String>(
                this,
                R.layout.room_name_layout,
                R.id.room_num)

        room_num.adapter = adapter

        val temp = ArrayList<String>()
        for (i in 0..99) {
            temp.add(i.toString())
        }
        adapter.addAll(temp)

        v_scroll_view.setListener(object : OberverScrollView.ScrollListener {
            override fun onYChange(y: Int) {
                room_num.scrollBy(0, y)
            }
        })
    }

    private fun loadRoomStatus() {

//        val dialog = WaitingDialog(this, "waiting", "请稍后...")
//        dialog.show()
//        mDoHttpHandler!!.post(object : ExceptionRunable() {
//            @Throws(Exception::class)
//            override fun doingBackground() {
//
//                val bean = LoginRequestBean()
//                bean.password = "96e79218965eb72c92a549dd5a330112"
//                bean.userAccountName = "18502938991"
//                val respUser = RestClient.SERVICES().userInfo(bean).execute()
//                val user = respUser.body()!!
//                val respRoomType = RestClient.SERVICES().roomType(user.authenticationType + " " + user.authToken).execute()
//                val roomTypes: List<RoomStatusListModel.RoomInfoBean>
//
//                if (respRoomType.body()!!.errorCode == 0) {
//                    roomTypes = respRoomType.body()!!.listhotelRoomInfo
//                } else {
//                    throw Exception(respRoomType.body()!!.message)
//                }
//
//                var index = 1
//
//                @SuppressLint("SimpleDateFormat") val format = SimpleDateFormat("yyyy-MM-dd")
//
//                val today = format.format(Date())
//
//                val roomStatus = ArrayList<RoomStatusModel>()
//                while ((index - 1) * 8 < roomTypes.size) {
//                    val respStatus = RestClient.SERVICES().roomStatus(user.authenticationType + " " + user.authToken, today, index).execute()
//                    val temp = respStatus.body()
//                    roomStatus.addAll(temp!!)
//                    index++
//                }
//
//
//                val roomList = ArrayList<RoomStatusListModel>()
//                var tempIndex = 0
//                for (i in roomTypes.indices) {
//                    val model = RoomStatusListModel()
//                    model.roomInfo = roomTypes[i]
//
//                    val statusModels = ArrayList<RoomStatusModel>()
//                    for (j in 0..35) {
//                        statusModels.add(roomStatus[tempIndex])
//                        tempIndex++
//                    }
//                    model.statusList = statusModels
//
//                    roomList.add(model)
//                }
//
//                dialog.dismiss()
//
//                mAdapter.setDates(roomList)
//            }
//
//            override fun onErr(e: Exception) {
//                if (dialog.isShowing) {
//                    dialog.dismiss()
//                }
//                ToastUnit.sortToase(e.message)
//            }
//
//
//        })

    }

}

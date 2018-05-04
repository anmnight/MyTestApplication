package com.example.testapp

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Process
import android.support.v4.content.PermissionChecker
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.TextView
import com.example.testapp.MainListAdapter.MainListAction
import com.example.testapp.app.HomeApplication
import com.example.testapp.app.Logger
import com.example.testapp.lesson_ui.RoundImageActivity
import com.example.testapp.lesson_android.*
import com.example.testapp.lesson_ui.MomentRefreshLayout
import com.example.testapp.lesson_ui.RoomStatusActivity
import com.example.testapp.lesson_ui.ScrollFlagActivity
import com.example.testapp.mytestapplication.R
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Thread.sleep


class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<MainListItemBean>()

    private var action: MainListAction = object : MainListAction {
        override fun itemTap(index: Int) {
            startActivity(Intent(this@MainActivity, itemList[index].cls))
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //require permission


        test_list.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MainListAdapter(this, action)
        test_list.adapter = adapter

        itemList.add(MainListItemBean(RoundImageActivity::class.java, "CustomerImage"))
        itemList.add(MainListItemBean(ScrollFlagActivity::class.java, "ScrollFlagDemo"))
        itemList.add(MainListItemBean(RoomStatusActivity::class.java, "RoomStatusDemo"))
        itemList.add(MainListItemBean(ThreadHandler::class.java, "HandlerThread"))
        itemList.add(MainListItemBean(MomentRefreshLayout::class.java, "MomentRefreshLayout"))

        adapter.setDates(itemList)



        val thread = ThreadTest()
        thread.testFutureTask()

    }




    /**
     * 启动系统服务
     */
    private fun startSystemService() {
        startService(Intent(this@MainActivity, UiService::class.java))
    }
}

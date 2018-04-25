package com.example.testapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.testapp.lesson_ui.CustomerViewTestActivity
import com.example.testapp.lesson_ui.RoundImageActivity
import com.example.testapp.lesson_android.*
import com.example.testapp.lesson_android_aidl.TestAIDLActivity
import com.example.testapp.lesson_ui.MomentRefreshLayout
import com.example.testapp.mytestapplication.R
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.ConcurrentHashMap

class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<MainListItemBean>()

    private var action: MainListAdapter.MainListAction = MainListAdapter.MainListAction { index -> startActivity(Intent(this@MainActivity, itemList[index].cls)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_list!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MainListAdapter(this, action)
        test_list!!.adapter = adapter

        itemList.add(MainListItemBean(RoundImageActivity::class.java, "CustomerImage"))
        itemList.add(MainListItemBean(ServiceActivity::class.java, "ServiceActivity"))
        itemList.add(MainListItemBean(ScrollFlagActivity::class.java, "ScrollFlagDemo"))
        itemList.add(MainListItemBean(RoomStatusActivity::class.java, "RoomStatusDemo"))
        itemList.add(MainListItemBean(ExecutorTest::class.java, "ExecutorTest"))
        itemList.add(MainListItemBean(ThreadHandler::class.java, "HandlerThread"))
        itemList.add(MainListItemBean(ExecutorServiceTest::class.java, "ExecutorServiceTest"))
        itemList.add(MainListItemBean(CustomerViewTestActivity::class.java, "CustomerViewTestActivity"))
        itemList.add(MainListItemBean(TestAIDLActivity::class.java, "AIDLActivity"))
        itemList.add(MainListItemBean(MomentRefreshLayout::class.java, "MomentRefreshLayout"))

        adapter.setDates(itemList)


        startActivity(Intent(this@MainActivity, MomentRefreshLayout::class.java))

//        val intent = Intent()
//        intent.action = "com.anmnight.aidl.service"
//        startService(intent)

        
    }
}

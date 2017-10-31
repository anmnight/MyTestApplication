package com.example.anxiao

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.anxiao.mytestapplication.customer_drawable.CustomerViewTestActivity
import com.example.anxiao.customer_drawable.RoundImageActivity
import com.example.anxiao.lesson_android.*
import com.example.anxiao.lesson_android_aidl.MyIntentService
import com.example.anxiao.lesson_android_aidl.TestAIDLActivity
import com.example.anxiao.lesson_gaodemap.BaseMapView
import com.example.anxiao.lesson_java.ListIterator
import com.example.anxiao.mytestapplication.R
import java.util.ArrayList
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val itemList = ArrayList<MainListItemBean>()

    private var action: MainListAdapter.MainListAction = MainListAdapter.MainListAction { index -> startActivity(Intent(this@MainActivity, itemList[index].cls)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_list!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MainListAdapter(this, action)
        test_list!!.adapter = adapter

        itemList.add(MainListItemBean(BaseMapView::class.java, "高德地图"))
        itemList.add(MainListItemBean(ListIterator::class.java, "队列遍历时不可操作，可用Iterator操作"))
        itemList.add(MainListItemBean(RoundImageActivity::class.java, "CustomerImage"))
        itemList.add(MainListItemBean(ServiceActivity::class.java, "ServiceActivity"))
        itemList.add(MainListItemBean(ScrollFlagActivity::class.java, "ScrollFlagDemo"))
        itemList.add(MainListItemBean(RoomStatusActivity::class.java, "RoomStatusDemo"))
        itemList.add(MainListItemBean(ExecutorTest::class.java, "ExecutorTest"))
        itemList.add(MainListItemBean(ThreadHandler::class.java, "HandlerThread"))
        itemList.add(MainListItemBean(ExecutorServiceTest::class.java, "ExecutorServiceTest"))
        itemList.add(MainListItemBean(CustomerViewTestActivity::class.java, "CustomerViewTestActivity"))
        itemList.add(MainListItemBean(TestAIDLActivity::class.java, "AIDLActivity"))

        adapter.setDates(itemList)


        val intent = Intent()
        intent.action = "com.anmnight.aidl.service"
        startService(intent)

    }
}

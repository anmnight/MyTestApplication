package com.example.anxiao.mytestapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

import com.example.anxiao.mytestapplication.customer_drawable.XfermodeRoundImageActivity
import com.example.anxiao.mytestapplication.jsheighway.HeightWayWebView
import com.example.anxiao.mytestapplication.lesson_android.RoomStatusActivity
import com.example.anxiao.mytestapplication.lesson_android.ScrollFlagActivity
import com.example.anxiao.mytestapplication.lesson_fresco.FrescoActivity
import com.example.anxiao.mytestapplication.lesson_gaodemap.BaseMapView
import com.example.anxiao.mytestapplication.lesson_java.ListIterator
import com.example.anxiao.mytestapplication.lesson_java.ServiceActivity
import com.example.anxiao.mytestapplication.lesson_java.ThreadHandler
import java.util.ArrayList
import com.example.anxiao.mytestapplication.lesson_java.ExecutorServiceTest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val testItem = ArrayList<MainListItemBean>()

    private var action: MainListAdapter.MainListAction = MainListAdapter.MainListAction { index -> startActivity(Intent(this@MainActivity, testItem[index].cls)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test_list!!.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val adapter = MainListAdapter(this, action)
        test_list!!.adapter = adapter

        val listIterator = MainListItemBean(ListIterator::class.java, "队列遍历时不可操作，可用Iterator操作")
        val mapDemo = MainListItemBean(BaseMapView::class.java, "高德地图")
        val frescoDemo = MainListItemBean(FrescoActivity::class.java, "fresco")
        val drawableDemo = MainListItemBean(XfermodeRoundImageActivity::class.java, "自定义图片")
        val serviceDemo = MainListItemBean(ServiceActivity::class.java, "service activity")
        val scrollFlagDemo = MainListItemBean(ScrollFlagActivity::class.java, "scrollFlagDemo")
        val roomStatusDemo = MainListItemBean(RoomStatusActivity::class.java, "RoomStatusDemo")

        testItem.add(listIterator)
        testItem.add(mapDemo)
        testItem.add(frescoDemo)
        testItem.add(drawableDemo)
        testItem.add(serviceDemo)
        testItem.add(scrollFlagDemo)
        testItem.add(roomStatusDemo)
        testItem.add(MainListItemBean(ThreadHandler::class.java, "HandlerThread"))
        testItem.add(MainListItemBean(HeightWayWebView::class.java, "HeightWayWebView"))
        testItem.add(MainListItemBean(ExecutorServiceTest::class.java,"ExecutorServiceTest"))

        adapter.setDates(testItem)


    }
}

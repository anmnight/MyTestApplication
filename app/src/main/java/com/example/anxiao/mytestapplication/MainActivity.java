package com.example.anxiao.mytestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anxiao.mytestapplication.customer_drawable.XfermodeRoundImageActivity;
import com.example.anxiao.mytestapplication.lesson_android.RoomStatusActivity;
import com.example.anxiao.mytestapplication.lesson_android.ScrollFlagActivity;
import com.example.anxiao.mytestapplication.lesson_fresco.FrescoActivity;
import com.example.anxiao.mytestapplication.lesson_gaodemap.BaseMapView;
import com.example.anxiao.mytestapplication.lesson_java.ListIterator;
import com.example.anxiao.mytestapplication.lesson_java.ServiceActivity;
import com.example.anxiao.mytestapplication.lesson_java.ThreadHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.test_list)
    RecyclerView mainList;

    private List<MainListItemBean> testItem = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mainList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        MainListAdapter adapter = new MainListAdapter(this, action);
        mainList.setAdapter(adapter);


        MainListItemBean listIterator = new MainListItemBean(ListIterator.class, "队列遍历时不可操作，可用Iterator操作");
        MainListItemBean mapDemo = new MainListItemBean(BaseMapView.class, "高德地图");
        MainListItemBean frescoDemo = new MainListItemBean(FrescoActivity.class, "fresco");
        MainListItemBean drawableDemo = new MainListItemBean(XfermodeRoundImageActivity.class, "自定义图片");
        MainListItemBean threadDemo = new MainListItemBean(ThreadHandler.class, "HandlerThread");
        MainListItemBean serviceDemo = new MainListItemBean(ServiceActivity.class, "service activity");
        MainListItemBean scrollFlagDemo = new MainListItemBean(ScrollFlagActivity.class, "scrollFlagDemo");
        MainListItemBean roomStatusDemo = new MainListItemBean(RoomStatusActivity.class, "RoomStatusDemo");

        testItem.add(listIterator);
        testItem.add(mapDemo);
        testItem.add(frescoDemo);
        testItem.add(drawableDemo);
        testItem.add(threadDemo);
        testItem.add(serviceDemo);
        testItem.add(scrollFlagDemo);
        testItem.add(roomStatusDemo);

        adapter.setDates(testItem);


    }

    MainListAdapter.MainListAction action = new MainListAdapter.MainListAction() {
        @Override
        public void itemTap(int index) {
            startActivity(new Intent(MainActivity.this, testItem.get(index).getCls()));
        }
    };
}

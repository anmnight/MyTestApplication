package com.example.anxiao.mytestapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.anxiao.mytestapplication.customer_drawable.XfermodeRoundImageActivity;
import com.example.anxiao.mytestapplication.lesson_fresco.FrescoActivity;
import com.example.anxiao.mytestapplication.lesson_gaodemap.BaseMapView;
import com.example.anxiao.mytestapplication.lesson_http.HttpTest;
import com.example.anxiao.mytestapplication.lesson_java.ListIterator;
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
        MainListItemBean httpDemo = new MainListItemBean(HttpTest.class, "retrofit");
        MainListItemBean frescoDemo = new MainListItemBean(FrescoActivity.class, "fresco");
        MainListItemBean drawableDemo = new MainListItemBean(XfermodeRoundImageActivity.class, "自定义图片");
        MainListItemBean threadDemo = new MainListItemBean(ThreadHandler.class, "HandlerThread");

        testItem.add(listIterator);
        testItem.add(mapDemo);
        testItem.add(httpDemo);
        testItem.add(frescoDemo);
        testItem.add(drawableDemo);
        testItem.add(threadDemo);

        adapter.setDates(testItem);


        double d = getDistance(118.767, 32.0415, 118.772, 32.0432);
        Logger.DEBUG("distance : " + d + "km");


    }


    /**
     * 计算两点之间真实距离
     *
     * @return KM
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 维度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;

        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;

        // 地球半径
        double R = 6371;

        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;

        return d;
    }


    MainListAdapter.MainListAction action = new MainListAdapter.MainListAction() {
        @Override
        public void itemTap(int index) {
            startActivity(new Intent(MainActivity.this, testItem.get(index).getCls()));
        }
    };
}

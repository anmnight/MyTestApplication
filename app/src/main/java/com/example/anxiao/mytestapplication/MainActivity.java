package com.example.anxiao.mytestapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.anxiao.mytestapplication.lesson_java.ListIterator;

import java.util.ArrayList;
import java.util.Iterator;
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

        testItem.add(listIterator);

        adapter.setDates(testItem);

    }

    MainListAdapter.MainListAction action = new MainListAdapter.MainListAction() {
        @Override
        public void itemTap(int index) {
            startActivity(new Intent(MainActivity.this, testItem.get(index).getCls()));
        }
    };
}

package com.example.anxiao.mytestapplication.lesson_fresco;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class FrescoActivity extends AppCompatActivity {


    private ThumbAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<String> list = loadPhoto();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putStringArrayList("list", list);
                message.setData(bundle);
                loadingHandler.sendMessage(message);
            }
        }).start();

//        LinearLayoutManager manager = new LinearLayoutManager(this);

        GridLayoutManager manager = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
        RecyclerView list = (RecyclerView) findViewById(R.id.list);
        list.setLayoutManager(manager);
        adapter = new ThumbAdapter(this);
        list.setAdapter(adapter);


    }

    Handler loadingHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            List<String> list = message.getData().getStringArrayList("list");
            adapter.setDates(list);
            return false;
        }
    });


    private ArrayList<String> loadPhoto() {
        ArrayList<String> paths = new ArrayList<>();
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        assert cursor != null;
        while (cursor.moveToNext()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            paths.add(path);
        }
        cursor.close();
        return paths;
    }

}

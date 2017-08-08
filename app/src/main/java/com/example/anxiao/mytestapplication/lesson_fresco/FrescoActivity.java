package com.example.anxiao.mytestapplication.lesson_fresco;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class FrescoActivity extends AppCompatActivity {

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


    }


    Handler loadingHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            Logger.DEBUG(message.getData().getStringArrayList("list").size());

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

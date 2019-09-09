package com.example.testapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.testapp.R;

public class DampLayoutActivity extends AppCompatActivity {

    private String tag = "DampLayoutActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.damplayout_actity);


        //https://segmentfault.com/a/1190000019272870
        RecyclerView list = findViewById(R.id.list);
        DampLayout dampLayout = findViewById(R.id.damp_layout);
//        dampLayout.setCallback(y -> Log.d(tag, "onScrollY : " + y));

        DampListAdapter adapter = new DampListAdapter(this);
        list.setAdapter(adapter);
        list.setLayoutManager(new LinearLayoutManager(this));


    }
}

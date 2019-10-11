package com.example.testapp.activities.android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.testapp.R;
import com.example.testapp.activities.android.components.FullScreenDialog;


public class BroadcastActivity extends AppCompatActivity {

    private BroadcastReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcost);

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.testapp.testbroadcast");
        receiver = initBroadcastReceiver();

        registerReceiver(receiver, filter);

        findViewById(R.id.d_button).setOnClickListener(v -> sendBroadcast(new Intent("com.example.testapp.testbroadcast")));

        findViewById(R.id.s_button).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setPackage("com.example.testapp");
            intent.setAction("com.example.testapp.STATIC");
            sendBroadcast(intent);
        });

        findViewById(R.id.dialog_button).setOnClickListener(v -> new FullScreenDialog().show(getSupportFragmentManager(), "dialog"));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


    private BroadcastReceiver initBroadcastReceiver() {
        return new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(BroadcastActivity.this, "get it", Toast.LENGTH_SHORT).show();
            }
        };
    }


}

package com.anmnight.remoteprocess;

import android.app.Activity;
import android.os.Bundle;

import com.anmnight.remoteprocess.unit.RoomLoginUnit;
import com.anmnight.remoteprocess.works.SystemLogWorker;

import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        PeriodicWorkRequest mPeriodicWorkRequest = new PeriodicWorkRequest.Builder(SystemLogWorker.class, 15, TimeUnit.MINUTES)
                .build();


        WorkManager.getInstance().enqueue(mPeriodicWorkRequest);

        final RoomLoginUnit loginUnit = new RoomLoginUnit();

        loginUnit.login(this);


    }
}

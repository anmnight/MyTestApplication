package com.example.anxiao.lesson_android;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.anxiao.app.Logger;
import com.example.anxiao.mytestapplication.R;
import com.example.anxiao.lesson_android.MyApplicationService;
import com.example.anxiao.lesson_android.MyBindService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceActivity extends AppCompatActivity {

    private Thread locThread;

    @BindView(R.id.bind_time)
    TextView bindTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);


        locThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                boolean exit = false;
                while (!exit) {
                    try {
                        Thread.sleep(1000);
                        Logger.debug("IM THREAD : " + i);
                        i += 1;
                    } catch (InterruptedException e) {
                        exit = true;
                    }
                }
            }
        });
        locThread.start();


    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBindService.MyBind bind = (MyBindService.MyBind) iBinder;
            bind.getService().setListener(new MyBindService.MyServiceAction() {
                @Override
                public void onProgress(final int progress) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bindTime.setText(String.valueOf(progress));
                        }
                    });
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Logger.debug("unbind service");
        }
    };

    private boolean isRunning = false;

    private void onBindService() {
        Intent intent = new Intent(this, MyBindService.class);
        isRunning = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void onUnbindService() {
        if (isRunning) {
            isRunning = false;
            unbindService(serviceConnection);
        }
    }


    @Override
    protected void onDestroy() {
        onUnbindService();
        super.onDestroy();
        locThread.interrupt();

    }

    @OnClick(R.id.bind_service)
    void bind_service() {
        onBindService();
    }

    @OnClick(R.id.unbind_service)
    void unbind_service() {
        onUnbindService();
    }

    @OnClick(R.id.start_service)
    void startService() {
        Intent intent = new Intent(this, MyApplicationService.class);
        startService(intent);
    }

    @OnClick(R.id.stop_service)
    void stopService() {
        Intent intent = new Intent(this, MyApplicationService.class);
        stopService(intent);
    }

}

package com.example.anxiao.mytestapplication.lesson_java;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;
import com.example.anxiao.mytestapplication.ToastUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThreadHandler extends AppCompatActivity {


    @BindView(R.id.thread_text)
    TextView threadTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handler);
        ButterKnife.bind(this);

        final HandlerThread handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();


        new Thread(new Runnable() {
            @Override
            public void run() {

                Handler handler = new Handler(handlerThread.getLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int i = 1;

                        while (true) {
                            try {
                                Thread.sleep(1000);
                                final int finalI = i;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        threadTextView.setText(String.valueOf(finalI));
                                    }
                                });
                                i += 1;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                    }
                });


            }
        }).start();




    }
}

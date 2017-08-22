package com.example.anxiao.mytestapplication.lesson_java;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.anxiao.mytestapplication.Logger;
import com.example.anxiao.mytestapplication.R;

public class ThreadHandler extends AppCompatActivity {

    Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_handler);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    handler = new Handler(new Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message message) {
                            Logger.DEBUG("coming message...");

                            return false;
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


//        //init handler thread
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                HandlerThread handlerThread = new HandlerThread("handlerThread");
//                handlerThread.start();
//
//                handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(Message message) {
//                        Logger.DEBUG("coming message...,im handlerThread");
//
//                        return false;
//                    }
//                });
//
//            }
//        }).start();


        final HandlerThread handlerThread = new HandlerThread("handlerThread");
        handlerThread.start();

        handler = new Handler(handlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                Logger.DEBUG("coming message...,im handlerThread");

                return false;
            }
        });


        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(2000);
                        if (handler != null) {
                            handler.sendEmptyMessage(0);
                        }
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Logger.DEBUG("im task 1");
                            }
                        });

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Logger.DEBUG("im task 2");
                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();


    }
}

package com.example.anxiao.mytestapplication.lesson_http;

import com.example.anxiao.mytestapplication.app.ToastUnit;

public abstract class ExceptionRunable implements Runnable {
    @Override
    public void run() {
        try {
            doingBackground();
        } catch (Exception e) {
            e.printStackTrace();
            ToastUnit.sortToase(e.getMessage());
        }
    }

    public abstract void doingBackground() throws Exception;
}

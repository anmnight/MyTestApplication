package com.example.anxiao.lesson_http;

public abstract class ExceptionRunable implements Runnable {
    @Override
    public void run() {
        try {
            doingBackground();
        } catch (Exception e) {
            onErr(e);
        }
    }

    public abstract void doingBackground() throws Exception;

    public abstract void onErr(Exception e);
}

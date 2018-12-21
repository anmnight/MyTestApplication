package com.example.testapp.java_thread;

import org.junit.Test;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/19 13:58
 * anmnight@qq.com
 */
public class Java_Wait_Notify_NotifyAll {

    @Test
    public void main(){
        final Something sth = new Something();
        Runnable runProduce = new Runnable() {
            int count = 4;

            @Override
            public void run() {
                while (count-- > 0) {
                    sth.produce();
                }
            }
        };
        Runnable runConsume = new Runnable() {
            int count = 4;

            @Override
            public void run() {
                while (count-- > 0) {
                    sth.consume();
                }
            }
        };
        for (int i = 0; i < 2; i++) {
            new Thread(runConsume).start();
        }
        for (int i = 0; i < 2; i++) {
            new Thread(runProduce).start();
        }


    }

}



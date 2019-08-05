package com.example.testapp.java_threadpool;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ThreadTest {


    @Test
    public void threadTest() {

        Lock lock = new ReentrantLock();

    }


    class Alipay {
        private double[] accounts;
        private Lock alipayLock;
        private Condition condition;

        public Alipay(int n, double money) {
            accounts = new double[n];
            alipayLock = new ReentrantLock();
            condition = alipayLock.newCondition();
            for (int i = 0; i < n; i++) {
                accounts[i] = money;
            }

        }

        public void transferA(int from, int to, int amount) throws InterruptedException {
            alipayLock.lock();
            try {
                while (accounts[from] < amount) {
                    //wait
                    condition.await();
                }

                accounts[from] = accounts[from] - amount;
                accounts[to] = accounts[to] + amount;
                condition.signalAll();
            } finally {
                alipayLock.unlock();
            }
        }

        public synchronized void transferB(int from, int to, int amount) throws InterruptedException {
            while (accounts[from] < amount) {
                //wait
                wait();
            }

            accounts[from] = accounts[from] - amount;
            accounts[to] = accounts[to] + amount;
            notifyAll();

        }

    }


}

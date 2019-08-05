package com.anmnight.threadtest;

import org.junit.Test;

import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

    private int queueSize = 10;
    private PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>(queueSize);

    @Test
    public void main() {

        PriorityBlockingQueueTest test = new PriorityBlockingQueueTest();


        Customer customer = test.new Customer();
        Producer producer = test.new Producer();

        customer.start();
        producer.start();
    }


    class Customer extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("队列空，等待数据..");
                    queue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    class Producer extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("队列满，等待空间..");
                queue.put(1);
            }
        }
    }
}

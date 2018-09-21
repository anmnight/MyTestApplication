package com.example.testapp.java;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/5 19:14
 * anmnight@qq.com
 */
public class TestClass {

    public void main() {


       Work work = new Work();
       Worker worker = new Worker();

       work.addObserver(worker);

       work.doWork();

    }
}

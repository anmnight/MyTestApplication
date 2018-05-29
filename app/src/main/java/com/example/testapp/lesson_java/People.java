package com.example.testapp.lesson_java;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/28 13:32
 * anmnight@qq.com
 */

public class People {
    String name;

    People(String name) {
        this.name = name;
    }

}


class Father extends People {
    Father(String name) {
        super(name);
    }
}
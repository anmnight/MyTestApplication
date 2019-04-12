package com.example.testapp.design.行为设计模式.observer;


public class WeiXinUser implements Observer {

    private String name;

    public WeiXinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + "_" + message);
    }
}

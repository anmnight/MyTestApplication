package com.example.testapp.design.结构设计模式.proxy;

public class Buyer implements IShop {
    @Override
    public void buy() {
        System.out.println("doing buy");
    }
}

package com.example.testapp.design.行为设计模式.template;

public class ZhangSanFeng extends AbstractSwordsman {
    @Override
    protected void neigong() {
        System.out.println("纯阳无极功");
    }

    @Override
    protected void useWeapons() {
        System.out.println("真武剑");
    }

    @Override
    protected void moves() {
        System.out.println("神门13剑");
    }

    @Override
    protected void hook() {
        System.out.println("意外事件");
    }
}

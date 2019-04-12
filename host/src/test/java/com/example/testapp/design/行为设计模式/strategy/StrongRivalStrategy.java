package com.example.testapp.design.行为设计模式.strategy;

public class StrongRivalStrategy implements FightStrategy {
    @Override
    public void fighting() {
        System.out.println("use strong");
    }
}

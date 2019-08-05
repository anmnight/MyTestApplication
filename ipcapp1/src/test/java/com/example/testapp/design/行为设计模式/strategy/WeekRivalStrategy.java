package com.example.testapp.design.行为设计模式.strategy;

public class WeekRivalStrategy implements FightStrategy {
    @Override
    public void fighting() {
        System.out.println("use week");
    }
}

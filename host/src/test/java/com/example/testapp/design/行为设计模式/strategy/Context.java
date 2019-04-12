package com.example.testapp.design.行为设计模式.strategy;

public class Context implements FightStrategy {

    private FightStrategy mFightStrategy;

    public Context(FightStrategy fightStrategy) {
        this.mFightStrategy = fightStrategy;
    }

    @Override
    public void fighting() {
        mFightStrategy.fighting();
    }
}

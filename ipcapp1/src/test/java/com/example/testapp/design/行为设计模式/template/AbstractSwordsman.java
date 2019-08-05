package com.example.testapp.design.行为设计模式.template;

public abstract class AbstractSwordsman {

    public final void fighting() {
        neigong();
        meridian();

        if (hasWeapons()) {
            useWeapons();
        }

        moves();

        hook();


    }

    protected abstract void neigong();

    protected void meridian() {
        System.out.println("开通经脉");
    }

    protected boolean hasWeapons() {
        return true;
    }

    protected abstract void useWeapons();

    protected abstract void moves();

    protected abstract void hook();
}

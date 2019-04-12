package com.example.testapp.design.行为设计模式.template;

public class ZhangWuJi extends AbstractSwordsman {
    @Override
    protected void neigong() {
        System.out.println("九阳神功");
    }

    @Override
    protected void useWeapons() {

    }

    @Override
    protected void moves() {
        System.out.println("使用乾坤大挪移");
    }

    @Override
    protected void hook() {

    }

    @Override
    protected boolean hasWeapons() {
        return false;
    }
}

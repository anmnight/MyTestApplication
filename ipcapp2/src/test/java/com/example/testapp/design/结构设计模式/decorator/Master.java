package com.example.testapp.design.结构设计模式.decorator;

public abstract class Master extends Swordman {
    private Swordman mSwordman;

    public Master(Swordman swordman) {
        this.mSwordman = swordman;
    }

    @Override
    public void attackMagic() {
        mSwordman.attackMagic();
    }
}

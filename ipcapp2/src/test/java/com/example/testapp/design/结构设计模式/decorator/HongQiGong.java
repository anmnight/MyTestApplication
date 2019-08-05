package com.example.testapp.design.结构设计模式.decorator;

public class HongQiGong extends Master {
    public HongQiGong(Swordman swordman) {
        super(swordman);
    }

    public void teachAttachMagic() {
        System.out.println("洪七公 使用 打狗棒");
        System.out.println("杨过 使用 打狗棒");
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttachMagic();
    }
}

package com.example.testapp.design.结构设计模式.decorator;

public class OuYangFeng extends Master {
    public OuYangFeng(Swordman swordman) {
        super(swordman);
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();
    }

    public void teachAttackMagic(){
        System.out.println("洪七公教授蛤蟆功");
        System.out.println("杨过使用打蛤蟆功");
    }
}

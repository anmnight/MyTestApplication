package com.example.testapp.design.结构设计模式.flyweight;

public class Goods implements IGoods {

    private String name;
    private String version;

    Goods(String name) {
        this.name = name;
    }

    @Override
    public void showPrice(String version) {
        if(version.equals("32G")){
            System.out.println("price : 5999");
        }else if (version.equals("64G")){
            System.out.println("price : 6999");
        }
    }
}

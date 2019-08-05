package com.example.testapp.design.结构设计模式.flyweight;

import java.util.HashMap;
import java.util.Map;

public class GoodsFactory {
    private static Map<String, Goods> pool = new HashMap<>();

    public static Goods getGoods(String name) {
        if (pool.containsKey(name)) {
            System.out.println("cache");
            return pool.get(name);
        } else {
            Goods goods = new Goods(name);
            pool.put(name, goods);
            System.out.println("create");
            return goods;
        }

    }

}

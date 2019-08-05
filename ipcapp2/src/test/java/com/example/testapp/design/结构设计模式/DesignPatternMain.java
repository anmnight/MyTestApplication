package com.example.testapp.design.结构设计模式;

import com.example.testapp.design.结构设计模式.decorator.HongQiGong;
import com.example.testapp.design.结构设计模式.decorator.OuYangFeng;
import com.example.testapp.design.结构设计模式.decorator.YangGuo;
import com.example.testapp.design.结构设计模式.facade.ZhangWuJi;
import com.example.testapp.design.结构设计模式.flyweight.Goods;
import com.example.testapp.design.结构设计模式.flyweight.GoodsFactory;
import com.example.testapp.design.结构设计模式.proxy.Buyer;
import com.example.testapp.design.结构设计模式.proxy.DynamicProxy;
import com.example.testapp.design.结构设计模式.proxy.IShop;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * 从程序结构上解决模块之间的耦合问题
 */
public class DesignPatternMain {


    /**
     * 装饰模式
     *
     * 动态向对象添加一些额外职责
     *
     * 缺点
     * 所有对象继承与component，component发生修改，对应所有子类需修改。
     * 比继承更加灵活，更易出错
     * 装饰层数不易过多，影响效率
     *
     */
    @Test
    public void testDecorator() {
        YangGuo yangGuo = new YangGuo();

        HongQiGong hongQiGong = new HongQiGong(yangGuo);
        hongQiGong.attackMagic();

        OuYangFeng ouYangFeng = new OuYangFeng(yangGuo);
        ouYangFeng.attackMagic();

    }

    /**
     * 代理模式
     *
     * 为其对象提供一个代理，以控制对这个对象的访问
     */
    @Test
    public void testProxy() {

        IShop buyer = new Buyer();

        DynamicProxy dynamicProxy = new DynamicProxy(buyer);

        ClassLoader classLoader = buyer.getClass().getClassLoader();

        IShop proxyBuyer = (IShop) Proxy.newProxyInstance(classLoader, new Class[]{IShop.class}, dynamicProxy);

        proxyBuyer.buy();

    }

    /**
     * 外观/门面 模式
     *
     * 子系统的外部与内部的通信必须通过一个统一的对象进行。此模式提供一个高层接口，使子系统更易于使用。
     *
     * 缺点
     * 不符合开放封闭原则
     *
     */
    @Test
    public void testFacade(){

        ZhangWuJi zhangWuJi = new ZhangWuJi();

        zhangWuJi.QianKun();
        zhangWuJi.QiShang();

    }


    /**
     * 享元模式
     *
     * 使用共享对象有效的支持大量的细粒度的对象
     *
     * 场景
     * 系统中存在大量相似对象
     * 需要缓冲池的场景
     */
    @Test
    public void flyweight(){
        Goods goods1 = GoodsFactory.getGoods("ip7");
        goods1.showPrice("32G");

        Goods goods2 = GoodsFactory.getGoods("ip7");
        goods2.showPrice("64G");
    }

}

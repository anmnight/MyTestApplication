package com.example.testapp.design.行为设计模式;

import com.example.testapp.design.行为设计模式.observer.SubscriptionSubject;
import com.example.testapp.design.行为设计模式.observer.WeiXinUser;
import com.example.testapp.design.行为设计模式.strategy.Context;
import com.example.testapp.design.行为设计模式.strategy.WeekRivalStrategy;
import com.example.testapp.design.行为设计模式.template.ZhangSanFeng;
import com.example.testapp.design.行为设计模式.template.ZhangWuJi;

import org.junit.Test;

/**
 * 行为型模式主要处理类或者对象互相交互或者如何分配职责
 */
public class DesignPatternMain {


    /**
     * 策略模式
     * 定义一系列算法，将每个算法封装，他们可互相替换，使得算法独立于客户端而独立变化
     *
     * 复用性小，每个策略对应一个类
     */
    @Test
    public void testStrategy() {
        WeekRivalStrategy weekRivalStrategy = new WeekRivalStrategy();
        Context context = new Context(weekRivalStrategy);
        context.fighting();
    }


    /**
     * 模板模式
     *
     * 规定一系列算法步骤，将一些步骤延迟到子类中，使子类不定义算法结构即可重新定义某些算法特定步骤
     *
     * 缺点
     * 每个模板和子类都需要创建类，导致类个数增加
     *
     */
    @Test
    public void testTemplate() {
        ZhangSanFeng zhangSanFeng = new ZhangSanFeng();
        zhangSanFeng.fighting();
        ZhangWuJi zhangWuJi = new ZhangWuJi();
        zhangWuJi.fighting();
    }


    /**
     * 观察者模式
     *
     * 定义对象间一种一对多的依赖关系，每当一个对象改变状态时，则所有依赖于它的对象都会得到所有通知，并被自动更新
     *
     * 缺点
     * java消息通知为顺序执行，一个观察者卡顿会引起整体的执行效率
     *
     */
    @Test
    public void testObserver(){

        WeiXinUser weiXinUser1 = new WeiXinUser("Zhang");
        WeiXinUser weiXinUser2 = new WeiXinUser("Wang");
        WeiXinUser weiXinUser3 = new WeiXinUser("Li");

        SubscriptionSubject subscriptionSubject =new SubscriptionSubject();
        subscriptionSubject.attache(weiXinUser1);
        subscriptionSubject.attache(weiXinUser2);
        subscriptionSubject.attache(weiXinUser3);

        subscriptionSubject.notify("HelloWord");
    }



}

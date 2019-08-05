package com.example.testapp.design.行为设计模式.observer;

import java.util.ArrayList;
import java.util.List;


public class SubscriptionSubject implements Subject {

    private List<Observer> weixinUserList = new ArrayList<>();

    @Override
    public void attache(Observer observer) {
        weixinUserList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        weixinUserList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : weixinUserList) {
            observer.update(message);
        }
    }
}

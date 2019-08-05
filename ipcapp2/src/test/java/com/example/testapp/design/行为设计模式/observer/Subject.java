package com.example.testapp.design.行为设计模式.observer;

public interface Subject {

    public void attache(Observer observer);

    public void detach(Observer observer);

    public void notify(String message);
}

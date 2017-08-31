package com.example.anxiao.mytestapplication.app;

class MainListItemBean {
    private Class<?> cls;
    private String describe;

    MainListItemBean(Class<?> cls, String describe) {
        this.cls = cls;
        this.describe = describe;
    }

    Class<?> getCls() {
        return cls;
    }

    String getDescribe() {
        return describe;
    }

}

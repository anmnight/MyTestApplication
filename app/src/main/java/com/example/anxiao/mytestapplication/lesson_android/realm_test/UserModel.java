package com.example.anxiao.mytestapplication.lesson_android.realm_test;

import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;


@RealmClass
public class UserModel {
    @PrimaryKey
    private String userId;
    private String userName;
    private int userTel;
    private String describ;

}

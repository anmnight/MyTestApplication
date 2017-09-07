package com.example.anxiao.mytestapplication.lesson_android.realm_test;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by anxiao on 2017/9/6.
 */

public class RealmUtils {
    private static class RealmUtilsHolder {
        static RealmUtils instance = new RealmUtils();
    }

    public static RealmUtils instance() {
        return RealmUtilsHolder.instance;
    }

    public Realm mRealm;

    public RealmUtils() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myrealm.realm") //文件名
                .schemaVersion(0) //版本号
                .build();
        mRealm = Realm.getInstance(config);
    }


}

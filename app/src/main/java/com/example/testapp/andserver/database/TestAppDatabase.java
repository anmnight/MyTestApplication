package com.example.testapp.andserver.database;

import com.example.testapp.BuildConfig;
import com.example.testapp.andserver.database.dao.BaseUserInfoDao;
import com.example.testapp.andserver.database.dao.CardFaceDao;
import com.example.testapp.andserver.database.dao.CardInfoDao;
import com.example.testapp.andserver.database.dao.SmsInfoDao;
import com.example.testapp.andserver.pojo.BaseUserInformation;
import com.example.testapp.andserver.pojo.CardFace;
import com.example.testapp.andserver.pojo.CardInfo;
import com.example.testapp.andserver.pojo.SmsInformation;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                BaseUserInformation.class,
                SmsInformation.class,
                CardInfo.class,
                CardFace.class},
        version = BuildConfig.VERSION_CODE,
        exportSchema = false)
public abstract class TestAppDatabase extends RoomDatabase {

    public abstract BaseUserInfoDao baseUserInfoDao();

    public abstract SmsInfoDao smsInfoDao();

    public abstract CardInfoDao cardInfoDao();

    public abstract CardFaceDao cardFaceDao();
}

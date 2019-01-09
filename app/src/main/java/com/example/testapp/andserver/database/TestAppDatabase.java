package com.example.testapp.andserver.database;

import com.example.testapp.andserver.database.dao.BaseUserInfoDao;
import com.example.testapp.andserver.pojo.BaseUserInfomation;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BaseUserInfomation.class}, version = 1, exportSchema = false)
public abstract class TestAppDatabase extends RoomDatabase {

    public abstract BaseUserInfoDao baseUserInfoDao();
}

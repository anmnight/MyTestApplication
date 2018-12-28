package com.anmnight.remoteprocess.database;

import com.anmnight.remoteprocess.database.dao.SystemLogDao;
import com.anmnight.remoteprocess.pojo.SystemLog;

import androidx.room.Database;
import androidx.room.RoomDatabase;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/27 13:30
 * anmnight@qq.com
 */

@Database(version = 1, entities = {SystemLog.class}, exportSchema = false)
public abstract class RemoteDatabase extends RoomDatabase {

    public abstract SystemLogDao systemLogDao();

}

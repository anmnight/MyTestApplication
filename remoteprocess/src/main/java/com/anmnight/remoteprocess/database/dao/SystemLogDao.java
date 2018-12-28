package com.anmnight.remoteprocess.database.dao;

import com.anmnight.remoteprocess.pojo.SystemLog;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/12/27 13:56
 * anmnight@qq.com
 */

@Dao
public interface SystemLogDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void inserts(SystemLog... logs);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(SystemLog log);


    @Query("SELECT * FROM system_log LIMIT 1")
    public SystemLog queryNow();

}

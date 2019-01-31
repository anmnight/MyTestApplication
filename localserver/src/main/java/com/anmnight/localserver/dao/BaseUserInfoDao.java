package com.anmnight.localserver.dao;

import com.anmnight.localserver.pojo.BaseUserInformation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BaseUserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(BaseUserInformation baseUserInfo);


    @Query("SELECT * FROM base_user_info where phone Like :phone ")
    public BaseUserInformation queryByPhone(Long phone);


}

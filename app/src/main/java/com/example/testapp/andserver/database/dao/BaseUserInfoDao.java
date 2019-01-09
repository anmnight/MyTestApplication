package com.example.testapp.andserver.database.dao;

import com.example.testapp.andserver.pojo.BaseUserInfomation;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BaseUserInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(BaseUserInfomation baseUserInfo);


    @Query("SELECT * FROM base_user_info where order_id Like :orderId ")
    public BaseUserInfomation queryById(String orderId);


}

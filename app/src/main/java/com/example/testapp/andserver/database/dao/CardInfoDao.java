package com.example.testapp.andserver.database.dao;

import com.example.testapp.andserver.pojo.CardInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CardInfoDao {

    @Query("SELECT * FROM card_info")
    public List<CardInfo> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(CardInfo cardInfo);
}

package com.anmnight.localserver.dao;

import com.anmnight.localserver.pojo.CardInfo;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CardInfoDao {

    @Query("SELECT * FROM card_info WHERE STATE LIKE 1")
    public List<CardInfo> findAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(CardInfo cardInfo);
}

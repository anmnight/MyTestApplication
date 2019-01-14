package com.example.testapp.andserver.database.dao;

import com.example.testapp.andserver.pojo.CardFace;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface CardFaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(CardFace cardFace);

}

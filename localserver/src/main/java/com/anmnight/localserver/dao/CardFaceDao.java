package com.anmnight.localserver.dao;

import com.anmnight.localserver.pojo.CardFace;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface CardFaceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(CardFace cardFace);


    @Query("SELECT * FROM card_face WHERE CARDCODE LIKE :code")
    public CardFace queryByCardCode(String code);
}

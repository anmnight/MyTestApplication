package com.anmnight.localserver.dao;


import com.anmnight.localserver.pojo.SmsInformation;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SmsInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(SmsInformation smsInfo);


    @Query("SELECT * FROM sms_info where phone Like :phone and content LIKE :code")
    public List<SmsInformation> verify(Long phone, int code);
}

package com.anmnight.localserver;


import com.anmnight.localserver.dao.BaseUserInfoDao;
import com.anmnight.localserver.dao.CardFaceDao;
import com.anmnight.localserver.dao.CardInfoDao;
import com.anmnight.localserver.dao.SmsInfoDao;
import com.anmnight.localserver.pojo.BaseUserInformation;
import com.anmnight.localserver.pojo.CardFace;
import com.anmnight.localserver.pojo.CardInfo;
import com.anmnight.localserver.pojo.SmsInformation;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(
        entities = {
                BaseUserInformation.class,
                SmsInformation.class,
                CardInfo.class,
                CardFace.class},
        version = BuildConfig.VERSION_CODE,
        exportSchema = false)
public abstract class LocalServerDatabase extends RoomDatabase {

    public abstract BaseUserInfoDao baseUserInfoDao();

    public abstract SmsInfoDao smsInfoDao();

    public abstract CardInfoDao cardInfoDao();

    public abstract CardFaceDao cardFaceDao();
}

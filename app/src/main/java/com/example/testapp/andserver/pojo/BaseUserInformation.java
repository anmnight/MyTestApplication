package com.example.testapp.andserver.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "base_user_info")
public class BaseUserInformation implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "phone")
    public Long phoneNum;

    public String chName;
    public String idNum;
    public String idNumStart;
    public String idNumEnd;

    public BaseUserInformation() {
    }


    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getChName() {
        return chName;
    }

    public void setChName(String chName) {
        this.chName = chName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getIdNumStart() {
        return idNumStart;
    }

    public void setIdNumStart(String idNumStart) {
        this.idNumStart = idNumStart;
    }

    public String getIdNumEnd() {
        return idNumEnd;
    }

    public void setIdNumEnd(String idNumEnd) {
        this.idNumEnd = idNumEnd;
    }

    public BaseUserInformation(Parcel in) {
        if (in.readByte() == 0) {
            phoneNum = null;
        } else {
            phoneNum = in.readLong();
        }
        chName = in.readString();
        idNum = in.readString();
        idNumStart = in.readString();
        idNumEnd = in.readString();
    }

    public static final Creator<BaseUserInformation> CREATOR = new Creator<BaseUserInformation>() {
        @Override
        public BaseUserInformation createFromParcel(Parcel in) {
            return new BaseUserInformation(in);
        }

        @Override
        public BaseUserInformation[] newArray(int size) {
            return new BaseUserInformation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (phoneNum == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(phoneNum);
        }
        dest.writeString(chName);
        dest.writeString(idNum);
        dest.writeString(idNumStart);
        dest.writeString(idNumEnd);
    }
}

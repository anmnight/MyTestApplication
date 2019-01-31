package com.anmnight.localserver.pojo;

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

    public String chnName;
    public String engName;
    public String idNum;
    public String idStartDate;
    public String idEndDate;

    public BaseUserInformation() {
    }


    public Long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getChnName() {
        return chnName;
    }

    public void setChnName(String chnName) {
        this.chnName = chnName;
    }

    public String getEngName() {
        return engName;
    }

    public void setEngName(String engName) {
        this.engName = engName;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getIdStartDate() {
        return idStartDate;
    }

    public void setIdStartDate(String idStartDate) {
        this.idStartDate = idStartDate;
    }

    public String getIdEndDate() {
        return idEndDate;
    }

    public void setIdEndDate(String idEndDate) {
        this.idEndDate = idEndDate;
    }

    protected BaseUserInformation(Parcel in) {
        if (in.readByte() == 0) {
            phoneNum = null;
        } else {
            phoneNum = in.readLong();
        }
        chnName = in.readString();
        engName = in.readString();
        idNum = in.readString();
        idStartDate = in.readString();
        idEndDate = in.readString();
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
        dest.writeString(chnName);
        dest.writeString(engName);
        dest.writeString(idNum);
        dest.writeString(idStartDate);
        dest.writeString(idEndDate);
    }
}

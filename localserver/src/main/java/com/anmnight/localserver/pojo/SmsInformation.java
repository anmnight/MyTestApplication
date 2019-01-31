package com.anmnight.localserver.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sms_info")
public class SmsInformation implements Parcelable {

    public SmsInformation() {
    }

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public Long phone;
    public String content;

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SmsInformation(Parcel in) {
        if (in.readByte() == 0) {
            phone = null;
        } else {
            phone = in.readLong();
        }
        content = in.readString();
    }

    public static final Creator<SmsInformation> CREATOR = new Creator<SmsInformation>() {
        @Override
        public SmsInformation createFromParcel(Parcel in) {
            return new SmsInformation(in);
        }

        @Override
        public SmsInformation[] newArray(int size) {
            return new SmsInformation[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (phone == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(phone);
        }
        dest.writeString(content);
    }
}

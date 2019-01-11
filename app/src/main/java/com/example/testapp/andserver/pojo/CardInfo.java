package com.example.testapp.andserver.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_info")
public class CardInfo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    public Long id;

    public String cardName;
    public String desp;
    public String orgCode;


    public CardInfo() {
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public CardInfo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        cardName = in.readString();
        desp = in.readString();
        orgCode = in.readString();
    }

    public static final Creator<CardInfo> CREATOR = new Creator<CardInfo>() {
        @Override
        public CardInfo createFromParcel(Parcel in) {
            return new CardInfo(in);
        }

        @Override
        public CardInfo[] newArray(int size) {
            return new CardInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(cardName);
        dest.writeString(desp);
        dest.writeString(orgCode);
    }
}

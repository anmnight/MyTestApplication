package com.example.testapp.andserver.pojo;

import android.os.Parcel;
import android.os.Parcelable;

public class CardInfoHttpResponse implements Parcelable {

    public String cardID;

    public String cardName;

    public String cardDesp;

    public String facePath;


    public CardInfoHttpResponse() {
    }

    public String getCardID() {
        return cardID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardDesp() {
        return cardDesp;
    }

    public void setCardDesp(String cardDesp) {
        this.cardDesp = cardDesp;
    }

    public String getFacePath() {
        return facePath;
    }

    public void setFacePath(String facePath) {
        this.facePath = facePath;
    }

    protected CardInfoHttpResponse(Parcel in) {
        cardID = in.readString();
        cardName = in.readString();
        cardDesp = in.readString();
        facePath = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cardID);
        dest.writeString(cardName);
        dest.writeString(cardDesp);
        dest.writeString(facePath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardInfoHttpResponse> CREATOR = new Creator<CardInfoHttpResponse>() {
        @Override
        public CardInfoHttpResponse createFromParcel(Parcel in) {
            return new CardInfoHttpResponse(in);
        }

        @Override
        public CardInfoHttpResponse[] newArray(int size) {
            return new CardInfoHttpResponse[size];
        }
    };
}

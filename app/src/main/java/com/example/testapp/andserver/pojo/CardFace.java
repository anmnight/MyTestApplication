package com.example.testapp.andserver.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_face")
public class CardFace implements Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "ID")
    @NonNull
    public String id;

    @ColumnInfo(name = "STATE")
    public boolean state;

    @ColumnInfo(name = "UPDATETIME")
    public String updateTime;

    @ColumnInfo(name = "CARDCODE")
    public String cardCode;

    @ColumnInfo(name = "CARDPRODUCTS")
    public String cardProducts;

    @ColumnInfo(name = "ZUCARD")
    public String zuCard;

    @ColumnInfo(name = "CARDRANK")
    public String cardRank;

    @ColumnInfo(name = "FACECODE")
    public String faceCode;

    @ColumnInfo(name = "SURFACENAME")
    public String surfaceName;

    @ColumnInfo(name = "CARDPRICE")
    public float cardPrice;


    public CardFace() {

    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public String getCardProducts() {
        return cardProducts;
    }

    public void setCardProducts(String cardProducts) {
        this.cardProducts = cardProducts;
    }

    public String getZuCard() {
        return zuCard;
    }

    public void setZuCard(String zuCard) {
        this.zuCard = zuCard;
    }

    public String getCardRank() {
        return cardRank;
    }

    public void setCardRank(String cardRank) {
        this.cardRank = cardRank;
    }

    public String getFaceCode() {
        return faceCode;
    }

    public void setFaceCode(String faceCode) {
        this.faceCode = faceCode;
    }

    public String getSurfaceName() {
        return surfaceName;
    }

    public void setSurfaceName(String surfaceName) {
        this.surfaceName = surfaceName;
    }

    public float getCardPrice() {
        return cardPrice;
    }

    public void setCardPrice(float cardPrice) {
        this.cardPrice = cardPrice;
    }

    protected CardFace(Parcel in) {
        id = in.readString();
        state = in.readByte() != 0;
        updateTime = in.readString();
        cardCode = in.readString();
        cardProducts = in.readString();
        zuCard = in.readString();
        cardRank = in.readString();
        faceCode = in.readString();
        surfaceName = in.readString();
        cardPrice = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeByte((byte) (state ? 1 : 0));
        dest.writeString(updateTime);
        dest.writeString(cardCode);
        dest.writeString(cardProducts);
        dest.writeString(zuCard);
        dest.writeString(cardRank);
        dest.writeString(faceCode);
        dest.writeString(surfaceName);
        dest.writeFloat(cardPrice);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CardFace> CREATOR = new Creator<CardFace>() {
        @Override
        public CardFace createFromParcel(Parcel in) {
            return new CardFace(in);
        }

        @Override
        public CardFace[] newArray(int size) {
            return new CardFace[size];
        }
    };
}

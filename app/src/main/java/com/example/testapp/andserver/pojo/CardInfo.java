package com.example.testapp.andserver.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "card_info")
public class CardInfo implements Parcelable{

    @PrimaryKey
    @ColumnInfo(name = "ID")
    @NonNull
    public String id;

    @ColumnInfo(name = "STATE")
    public boolean state;

    @ColumnInfo(name = "UPDATETIME")
    public String updateTime;

    @ColumnInfo(name = "PRODUCTSN")
    public int productSN;

    @ColumnInfo(name = "PRODUCTNAME")
    public String productName;

    @ColumnInfo(name = "ORGANIZATIONSN")
    public String organizationSN;

    @ColumnInfo(name = "GOLDMARK")
    public String goldMark;

    @ColumnInfo(name = "BUSINESSTYPE")
    public String businessType;

    @ColumnInfo(name = "ORIGINID")
    public String originId;

    @ColumnInfo(name = "CITYID")
    public String cityId;

    @ColumnInfo(name = "UPGRADE")
    public String upGrade;

    @ColumnInfo(name = "IS_VISAEMV")
    public boolean isVisaEmv;

    @ColumnInfo(name = "IS_MASTEREMV")
    public boolean isMasterEmv;

    @ColumnInfo(name = "EMV_USECITY")
    public String emvUseCity;

    @ColumnInfo(name = "PRODUCT_SORT")
    public int productSort;

    @ColumnInfo(name = "IS_SECONDS")
    public boolean isSeconds;

    @ColumnInfo(name = "IS_APPLY_DEBIT")
    public boolean isApplyDebit;

    @ColumnInfo(name = "CARD_SHOW_TYPE")
    public String cardShowType;

    @ColumnInfo(name = "IS_YOUTH_PROCESS")
    public boolean isYouthProcess;

    @ColumnInfo(name = "IS_CAMPUS_PROCESS")
    public boolean isCampusProcess;

    @ColumnInfo(name = "CARD_AGE_LIMIT")
    public int cardAgeLimit;

    @ColumnInfo(name = "CARD_PRODUCT_CATEGORY")
    public String cardProductCategory;

    @ColumnInfo(name = "CARD_PRODUCT_INFO")
    public String cardProductInfo;

    public CardInfo() {
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

    public int getProductSN() {
        return productSN;
    }

    public void setProductSN(int productSN) {
        this.productSN = productSN;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrganizationSN() {
        return organizationSN;
    }

    public void setOrganizationSN(String organizationSN) {
        this.organizationSN = organizationSN;
    }

    public String getGoldMark() {
        return goldMark;
    }

    public void setGoldMark(String goldMark) {
        this.goldMark = goldMark;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getUpGrade() {
        return upGrade;
    }

    public void setUpGrade(String upGrade) {
        this.upGrade = upGrade;
    }

    public boolean isVisaEmv() {
        return isVisaEmv;
    }

    public void setVisaEmv(boolean visaEmv) {
        isVisaEmv = visaEmv;
    }

    public boolean isMasterEmv() {
        return isMasterEmv;
    }

    public void setMasterEmv(boolean masterEmv) {
        isMasterEmv = masterEmv;
    }

    public String getEmvUseCity() {
        return emvUseCity;
    }

    public void setEmvUseCity(String emvUseCity) {
        this.emvUseCity = emvUseCity;
    }

    public int getProductSort() {
        return productSort;
    }

    public void setProductSort(int productSort) {
        this.productSort = productSort;
    }

    public boolean isSeconds() {
        return isSeconds;
    }

    public void setSeconds(boolean seconds) {
        isSeconds = seconds;
    }

    public boolean isApplyDebit() {
        return isApplyDebit;
    }

    public void setApplyDebit(boolean applyDebit) {
        isApplyDebit = applyDebit;
    }

    public String getCardShowType() {
        return cardShowType;
    }

    public void setCardShowType(String cardShowType) {
        this.cardShowType = cardShowType;
    }

    public boolean isYouthProcess() {
        return isYouthProcess;
    }

    public void setYouthProcess(boolean youthProcess) {
        isYouthProcess = youthProcess;
    }

    public boolean isCampusProcess() {
        return isCampusProcess;
    }

    public void setCampusProcess(boolean campusProcess) {
        isCampusProcess = campusProcess;
    }

    public int getCardAgeLimit() {
        return cardAgeLimit;
    }

    public void setCardAgeLimit(int cardAgeLimit) {
        this.cardAgeLimit = cardAgeLimit;
    }

    public String getCardProductCategory() {
        return cardProductCategory;
    }

    public void setCardProductCategory(String cardProductCategory) {
        this.cardProductCategory = cardProductCategory;
    }

    public String getCardProductInfo() {
        return cardProductInfo;
    }

    public void setCardProductInfo(String cardProductInfo) {
        this.cardProductInfo = cardProductInfo;
    }

    protected CardInfo(Parcel in) {
        id = in.readString();
        state = in.readByte() != 0;
        updateTime = in.readString();
        productSN = in.readInt();
        productName = in.readString();
        organizationSN = in.readString();
        goldMark = in.readString();
        businessType = in.readString();
        originId = in.readString();
        cityId = in.readString();
        upGrade = in.readString();
        isVisaEmv = in.readByte() != 0;
        isMasterEmv = in.readByte() != 0;
        emvUseCity = in.readString();
        productSort = in.readInt();
        isSeconds = in.readByte() != 0;
        isApplyDebit = in.readByte() != 0;
        cardShowType = in.readString();
        isYouthProcess = in.readByte() != 0;
        isCampusProcess = in.readByte() != 0;
        cardAgeLimit = in.readInt();
        cardProductCategory = in.readString();
        cardProductInfo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeByte((byte) (state ? 1 : 0));
        dest.writeString(updateTime);
        dest.writeInt(productSN);
        dest.writeString(productName);
        dest.writeString(organizationSN);
        dest.writeString(goldMark);
        dest.writeString(businessType);
        dest.writeString(originId);
        dest.writeString(cityId);
        dest.writeString(upGrade);
        dest.writeByte((byte) (isVisaEmv ? 1 : 0));
        dest.writeByte((byte) (isMasterEmv ? 1 : 0));
        dest.writeString(emvUseCity);
        dest.writeInt(productSort);
        dest.writeByte((byte) (isSeconds ? 1 : 0));
        dest.writeByte((byte) (isApplyDebit ? 1 : 0));
        dest.writeString(cardShowType);
        dest.writeByte((byte) (isYouthProcess ? 1 : 0));
        dest.writeByte((byte) (isCampusProcess ? 1 : 0));
        dest.writeInt(cardAgeLimit);
        dest.writeString(cardProductCategory);
        dest.writeString(cardProductInfo);
    }

    @Override
    public int describeContents() {
        return 0;
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
}

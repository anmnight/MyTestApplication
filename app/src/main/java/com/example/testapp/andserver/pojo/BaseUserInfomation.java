package com.example.testapp.andserver.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "base_user_info")
public class BaseUserInfomation {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_id")
    private Long orderId;

    private String chName;
    private String idNum;
    private String idNumStart;
    private String idNumEnd;
    private int phoneNum;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    public int getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(int phoneNum) {
        this.phoneNum = phoneNum;
    }
}

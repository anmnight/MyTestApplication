package com.example.testapp.lesson_ui.roomstatus;

/**
 * Created by anxiao on 2017/9/1.
 */

public class RoomStatusModel {
    /**
     * id : 102-2017-08-26
     * date : 2017-08-26
     * roomNo : 102
     * orderDetailId :
     * orderId :
     * guestName :
     * roomStatus : V
     * guestMobile :
     * roomType :
     * isDirty : false
     * isHourRent : false
     * makeCollections : 0.0
     * sourceChannel :
     * roomRate : 0.0
     * days : 1
     * remark :
     */

    private String id;
    private String date;
    private String roomNo;
    private String orderDetailId;
    private String orderId;
    private String guestName;
    private String roomStatus;
    private String guestMobile;
    private String roomType;
    private boolean isDirty;
    private boolean isHourRent;
    private double makeCollections;
    private String sourceChannel;
    private double roomRate;
    private int days;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getGuestMobile() {
        return guestMobile;
    }

    public void setGuestMobile(String guestMobile) {
        this.guestMobile = guestMobile;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public boolean isIsDirty() {
        return isDirty;
    }

    public void setIsDirty(boolean isDirty) {
        this.isDirty = isDirty;
    }

    public boolean isIsHourRent() {
        return isHourRent;
    }

    public void setIsHourRent(boolean isHourRent) {
        this.isHourRent = isHourRent;
    }

    public double getMakeCollections() {
        return makeCollections;
    }

    public void setMakeCollections(double makeCollections) {
        this.makeCollections = makeCollections;
    }

    public String getSourceChannel() {
        return sourceChannel;
    }

    public void setSourceChannel(String sourceChannel) {
        this.sourceChannel = sourceChannel;
    }

    public double getRoomRate() {
        return roomRate;
    }

    public void setRoomRate(double roomRate) {
        this.roomRate = roomRate;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

package com.anmnight.remoteprocess.pojo;

import java.util.List;

public class RoomTypeInfo {

    /**
     * RoomTypeInfo : {"RoomNo":"103","RoomTypeCode":"M085","RoomTypeName":"双人标准间","IsValid":true,"Remark":"舒适的双人间，带独立卫浴间，24小时热水。","RoomStatusCode":"VC"}
     * StatusList : [{"id":"102-2017-08-26","date":"2017-08-26","roomNo":"102","orderDetailId":"","orderId":"","guestName":"","roomStatus":"V","guestMobile":"","roomType":"","isDirty":false,"isHourRent":false,"makeCollections":0,"sourceChannel":"","roomRate":0,"days":1,"remark":""}]
     */

    private String roomCode;
    private String roomName;
    private List<RoomInfoBean> status;

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public List<RoomInfoBean> getStatus() {
        return status;
    }

    public void setStatus(List<RoomInfoBean> status) {
        this.status = status;
    }

    public static class RoomInfoBean {
        /**
         * RoomNo : 103
         * RoomTypeCode : M085
         * RoomTypeName : 双人标准间
         * IsValid : true
         * Remark : 舒适的双人间，带独立卫浴间，24小时热水。
         * RoomStatusCode : VC
         */

        private String userName;
        private boolean isClean;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public boolean isClean() {
            return isClean;
        }

        public void setClean(boolean clean) {
            isClean = clean;
        }
    }


}
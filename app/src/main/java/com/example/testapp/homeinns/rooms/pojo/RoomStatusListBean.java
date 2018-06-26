package com.example.testapp.homeinns.rooms.pojo;

import java.util.List;

/**
 * Created by anxiao on 2017/9/1.
 */

public class RoomStatusListBean {

    /**
     * RoomInfo : {"RoomNo":"103","RoomTypeCode":"M085","RoomTypeName":"双人标准间","IsValid":true,"Remark":"舒适的双人间，带独立卫浴间，24小时热水。","RoomStatusCode":"VC"}
     * StatusList : [{"id":"102-2017-08-26","date":"2017-08-26","roomNo":"102","orderDetailId":"","orderId":"","guestName":"","roomStatus":"V","guestMobile":"","roomType":"","isDirty":false,"isHourRent":false,"makeCollections":0,"sourceChannel":"","roomRate":0,"days":1,"remark":""}]
     */

    private RoomInfoBean RoomInfo;
    private List<RoomStatusBean> StatusList;

    public RoomInfoBean getRoomInfo() {
        return RoomInfo;
    }

    public void setRoomInfo(RoomInfoBean RoomInfo) {
        this.RoomInfo = RoomInfo;
    }

    public List<RoomStatusBean> getStatusList() {
        return StatusList;
    }

    public void setStatusList(List<RoomStatusBean> StatusList) {
        this.StatusList = StatusList;
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

        private String RoomNo;
        private String RoomTypeCode;
        private String RoomTypeName;
        private boolean IsValid;
        private String Remark;
        private String RoomStatusCode;

        public String getRoomNo() {
            return RoomNo;
        }

        public void setRoomNo(String RoomNo) {
            this.RoomNo = RoomNo;
        }

        public String getRoomTypeCode() {
            return RoomTypeCode;
        }

        public void setRoomTypeCode(String RoomTypeCode) {
            this.RoomTypeCode = RoomTypeCode;
        }

        public String getRoomTypeName() {
            return RoomTypeName;
        }

        public void setRoomTypeName(String RoomTypeName) {
            this.RoomTypeName = RoomTypeName;
        }

        public boolean isIsValid() {
            return IsValid;
        }

        public void setIsValid(boolean IsValid) {
            this.IsValid = IsValid;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public String getRoomStatusCode() {
            return RoomStatusCode;
        }

        public void setRoomStatusCode(String RoomStatusCode) {
            this.RoomStatusCode = RoomStatusCode;
        }
    }

}

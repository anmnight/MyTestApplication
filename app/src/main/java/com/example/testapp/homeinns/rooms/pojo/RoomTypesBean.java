package com.example.testapp.homeinns.rooms.pojo;

import java.util.List;

/**
 * Created by anxiao on 2017/9/1.
 */

public class RoomTypesBean {

    /**
     * listhotelRoomInfo : [{"RoomNo":"102","RoomTypeCode":"M085","RoomTypeName":"双人标准间","IsValid":true,"Remark":"舒适的双人间，带独立卫浴间，24小时热水。","RoomStatusCode":"VC"},{"RoomNo":"103","RoomTypeCode":"M085","RoomTypeName":"双人标准间","IsValid":true,"Remark":"舒适的双人间，带独立卫浴间，24小时热水。","RoomStatusCode":"VC"},{"RoomNo":"106","RoomTypeCode":"M086","RoomTypeName":"特色大炕房（家庭房）","IsValid":true,"Remark":"西北特色农家大炕，是家庭出游的理想选择","RoomStatusCode":"VC"},{"RoomNo":"108","RoomTypeCode":"M086","RoomTypeName":"特色大炕房（家庭房）","IsValid":true,"Remark":"西北特色农家大炕，是家庭出游的理想选择","RoomStatusCode":"VC"},{"RoomNo":"201","RoomTypeCode":"M087","RoomTypeName":"特色双人间","IsValid":true,"Remark":"简洁的装饰，舒适的床垫，独立卫浴，24小时热水","RoomStatusCode":"VC"},{"RoomNo":"202","RoomTypeCode":"M087","RoomTypeName":"特色双人间","IsValid":true,"Remark":"简洁的装饰，舒适的床垫，独立卫浴，24小时热水","RoomStatusCode":"VC"},{"RoomNo":"203","RoomTypeCode":"M087","RoomTypeName":"特色双人间","IsValid":true,"Remark":"简洁的装饰，舒适的床垫，独立卫浴，24小时热水","RoomStatusCode":"VC"},{"RoomNo":"210","RoomTypeCode":"M088","RoomTypeName":"特色大床房","IsValid":true,"Remark":"房间装饰简洁舒适，有独立卫浴","RoomStatusCode":"VC"},{"RoomNo":"211","RoomTypeCode":"M088","RoomTypeName":"特色大床房","IsValid":true,"Remark":"房间装饰简洁舒适，有独立卫浴","RoomStatusCode":"VC"}]
     * ErrorCode : 0
     * Message : 执行成功！
     */

    private int ErrorCode;
    private String Message;
    private List<RoomStatusListBean.RoomInfoBean> listhotelRoomInfo;

    public int getErrorCode() {
        return ErrorCode;
    }

    public void setErrorCode(int ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<RoomStatusListBean.RoomInfoBean> getListhotelRoomInfo() {
        return listhotelRoomInfo;
    }

    public void setListhotelRoomInfo(List<RoomStatusListBean.RoomInfoBean> listhotelRoomInfo) {
        this.listhotelRoomInfo = listhotelRoomInfo;
    }
}

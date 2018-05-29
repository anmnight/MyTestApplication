package com.example.testapp.homeinns.rooms.data.remote;

import com.example.testapp.homeinns.rooms.pojo.LoginBean;
import com.example.testapp.lesson_ui.roomstatus.ResponseRoomTypes;
import com.example.testapp.lesson_ui.roomstatus.RoomStatusModel;

import java.util.List;

import dagger.Module;
import http.bean.LoginRequestBean;
import http.bean.UserResponseBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anxiao on 2017/8/1.
 * request api
 */

public interface RoomApi {

    @POST("api/pmsaccount/login")
    Call<UserResponseBean> login(
            @Body LoginBean account);

    @GET("api/pms/data?url=api/v1/HotelRoom/GetQueryHotelRoomListJSON/X00105")
    Call<ResponseRoomTypes> roomType(
            @Header("Authorization") String headToken
    );

    @GET("api/bizroom/GetRoomStatus?hotelId=X00105&pageSize=8")
    Call<List<RoomStatusModel>> roomStatus(
            @Header("Authorization") String headToken,
            @Query("date") String today,
            @Query("pageIndex") int index
    );

}

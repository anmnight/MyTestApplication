package com.example.testapp.homeinns.rooms.data.remote;

import com.example.testapp.homeinns.rooms.pojo.LoginBean;
import com.example.testapp.homeinns.rooms.pojo.UserBean;
import com.example.testapp.homeinns.rooms.pojo.RoomTypesBean;
import com.example.testapp.homeinns.rooms.pojo.RoomStatusBean;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by anxiao on 2017/8/1.
 * request api
 */

public interface RoomApi {

    @POST("api/pmsaccount/login")
    @Headers({"Content-Type:application/json"})
    Call<UserBean> login(
            @Body LoginBean account);

    @GET("api/pms/data?url=api/v1/HotelRoom/GetQueryHotelRoomListJSON/X00105")
    Call<RoomTypesBean> roomType(
            @Header("Authorization") String headToken
    );

    @GET("api/bizroom/GetRoomStatus?hotelId=X00105&pageSize=8")
    Call<List<RoomStatusBean>> roomStatus(
            @Header("Authorization") String headToken,
            @Query("date") String today,
            @Query("pageIndex") int index
    );

}

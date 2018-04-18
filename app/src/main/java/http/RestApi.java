package http;

import com.example.testapp.lesson_android.roomstatus.ResponseRoomTypes;
import com.example.testapp.lesson_android.roomstatus.RoomStatusModel;

import java.util.List;

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

public interface RestApi {

    @POST("api/pmsaccount/login")
    Call<UserResponseBean> userInfo(
            @Body LoginRequestBean user);

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

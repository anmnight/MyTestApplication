package http;

import http.bean.UserRequestBean;
import http.bean.UserResponseBean;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by anxiao on 2017/8/1.
 * request api
 */

public interface RestApi {

    @POST("api/user/login")
    Call<BaseResponseBean<UserResponseBean>> userInfo(
            @Body UserRequestBean user);
}

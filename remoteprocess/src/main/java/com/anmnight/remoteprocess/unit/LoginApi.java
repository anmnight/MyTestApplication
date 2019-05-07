package com.anmnight.remoteprocess.unit;

import com.example.testapp.http.Call;
import com.example.testapp.http.core.Body;
import com.example.testapp.http.core.Post;
import com.anmnight.remoteprocess.pojo.PostLoginBean;
import com.anmnight.remoteprocess.pojo.LoginUser;

public interface LoginApi {

    @Post("api/pmsaccount/login")
    Call<LoginUser> login(@Body PostLoginBean account);
}

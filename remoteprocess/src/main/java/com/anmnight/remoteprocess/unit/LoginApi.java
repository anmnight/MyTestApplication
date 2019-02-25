package com.anmnight.remoteprocess.unit;

import com.anmnight.commlibrary.http.Call;
import com.anmnight.commlibrary.http.core.Body;
import com.anmnight.commlibrary.http.core.Post;
import com.anmnight.remoteprocess.pojo.PostLoginBean;
import com.anmnight.remoteprocess.pojo.LoginUser;

public interface LoginApi {

    @Post("api/pmsaccount/login")
    Call<LoginUser> login(@Body PostLoginBean account);
}

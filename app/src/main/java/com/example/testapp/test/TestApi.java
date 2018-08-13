package com.example.testapp.test;

import com.bankcomm.commlibrary.http.Call;
import com.bankcomm.commlibrary.http.core.Get;
import com.bankcomm.commlibrary.http.core.Params;


/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 16:56
 * anmnight@qq.com
 */
public interface TestApi {

    @Get("service/getIpInfo.php")
    Call<IpResult> getData(@Params("ip") String ip);
}

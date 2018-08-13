package com.bankcomm.commlibrary;

import com.bankcomm.commlibrary.http.core.Get;
import com.bankcomm.commlibrary.http.core.Post;

import okhttp3.Call;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 16:18
 * anmnight@qq.com
 */
public interface TestApi {

    @Post("abc/post")
    Call postTest();

    @Get("abc/get")
    Call getTest();
}


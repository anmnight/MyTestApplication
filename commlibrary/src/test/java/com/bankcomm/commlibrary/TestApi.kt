package com.bankcomm.commlibrary

import com.bankcomm.commlibrary.http.Call
import com.bankcomm.commlibrary.http.core.Body
import com.bankcomm.commlibrary.http.core.Get
import com.bankcomm.commlibrary.http.core.Params

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/17 15:44
 * anmnight@qq.com
 */
interface TestApi {

    @Get("/user")
    fun user(@Params("id") id: Int): Call<TestEntity>

    @Get("/version/all")
    fun version(): Call<Version>
}
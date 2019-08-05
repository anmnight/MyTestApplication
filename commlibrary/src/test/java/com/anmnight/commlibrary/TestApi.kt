package com.anmnight.commlibrary

import com.example.testapp.http.Call
import com.example.testapp.http.core.Body
import com.example.testapp.http.core.Get
import com.example.testapp.http.core.Query

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/17 15:44
 * anmnight@qq.com
 */
interface TestApi {

    @Get("/user")
    fun user(@Query("id") id: Int, @Body version: Version): Call<TestEntity>

    @Get("/version/all")
    fun version(): Call<Version>
}
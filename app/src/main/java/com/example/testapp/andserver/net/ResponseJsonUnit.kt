package com.example.testapp.andserver.net

import com.anmnight.commlibrary.unit.JsonUnit
import com.example.testapp.pojo.BaseResponse

object ResponseJsonUnit {

    fun successJson(data: Any): String {
        val resp = BaseResponse()
        resp.isSuccess = true
        resp.data = data
        return JsonUnit.objToString(resp)
    }

    fun failJson(message: String): String {
        val resp = BaseResponse()
        resp.isSuccess = false
        resp.message = message
        return JsonUnit.objToString(resp)
    }
}

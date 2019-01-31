package com.anmnight.commlibrary.unit

import com.google.gson.Gson
import java.lang.reflect.Type

object JsonUnit {

    fun objToString(obj: Any): String {
        return Gson().toJson(obj)
    }


    fun <T> stringToObject(string: String, type: Type): T {
        return Gson().fromJson(string, type)
    }
}

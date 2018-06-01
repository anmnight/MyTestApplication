package unit

import android.content.Context
import com.example.testapp.app.HomeApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/31 18:05
 * anmnight@qq.com
 */
class StoreUtil {

    private val baseURl = "https://hms.homeinns.com/"
    private val retrofit = Retrofit.Builder().baseUrl(baseURl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    private val ctx: Context

    private val user = "user"
    private val tokenKey = "user.key"

    init {
        this.ctx = HomeApplication.application
    }

    fun <T> request(api: Class<T>): T {
        return retrofit.create(api)
    }


    fun saveToken(token: String) {
        val editer = ctx.getSharedPreferences(user, Context.MODE_PRIVATE).edit()
        editer.putString(tokenKey, token)
        editer.apply()
    }

    fun getToken():String{
        val sharedperferace = ctx.getSharedPreferences(user, Context.MODE_PRIVATE)
        return sharedperferace.getString(tokenKey,"")
    }
}
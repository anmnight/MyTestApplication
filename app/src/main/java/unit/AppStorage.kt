package unit

import android.content.Context
import android.os.Environment
import com.example.testapp.app.HomeApplication
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject
import javax.inject.Singleton

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/5/31 18:05
 * anmnight@qq.com
 */

class AppStorage {

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

    fun getToken(): String {
        val sharedperferace = ctx.getSharedPreferences(user, Context.MODE_PRIVATE)
        return sharedperferace.getString(tokenKey, "")
    }

    fun saveToDisk(name: String) {
        val sf = File(Environment.DIRECTORY_DOCUMENTS)
        val file = File(sf, name)
        var fos: FileOutputStream? = null
        var inputs: InputStream? = null
        try {
            fos = FileOutputStream(file)
            inputs = file.inputStream()
            val buff = ByteArray(2048)
            val len = inputs.read(buff)
            while (len != -1) {
                fos.write(buff, 0, len)
            }
            fos.flush()
        } catch (e: Exception) {
            throw Exception(e.message)
        } finally {
            inputs?.close()
            fos?.close()
        }


    }
}
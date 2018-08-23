package com.bankcomm.commlibrary.storage

import android.content.Context
import java.io.File

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 20:44
 * anmnight@qq.com
 */
object SdPath {

    fun innerFile(ctx: Context): File = ctx.filesDir

    fun innerCache(ctx: Context): File = ctx.cacheDir

    fun externalFile(ctx: Context): File = ctx.getExternalFilesDir("/")

    fun externalCache(ctx: Context): File = ctx.externalCacheDir


}
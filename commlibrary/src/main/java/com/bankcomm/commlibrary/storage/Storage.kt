package com.bankcomm.commlibrary.storage

import java.io.File
import java.io.InputStream

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 20:42
 * anmnight@qq.com
 */
interface Storage {

    fun saveToSd(savedPath: String, savedName: String, inputStream: InputStream): Boolean

    fun readFromSd(savedPath: String, savedName: String): File

}
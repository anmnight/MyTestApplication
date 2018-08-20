package com.bankcomm.commlibrary.storage

import java.io.File
import java.io.FileInputStream

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/20 20:42
 * anmnight@qq.com
 */
interface FileHelper {

    fun saveToSd(stream: FileInputStream, sdPath: SdPath, fileName: String): Boolean

    fun readFromSd(sdPath: SdPath, fileName: String): File

}
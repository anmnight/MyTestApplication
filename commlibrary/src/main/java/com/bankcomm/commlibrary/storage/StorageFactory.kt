package com.bankcomm.commlibrary.storage

import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/22 11:08
 * anmnight@qq.com
 */
class StorageFactory : Storage {

    @Synchronized
    override fun saveToSd(savedPath: String, savedName: String, inputStream: InputStream): Boolean {

        val outputStream = FileOutputStream(File(savedPath, savedName))
        try {
            val buffer = ByteArray(1024)
            var len = 0
            while (len != -1) {
                len = inputStream.read(buffer)
                outputStream.write(buffer, 0, len)
            }

            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        } finally {
            outputStream.close()
            inputStream.close()
        }
    }

    @Synchronized
    override fun readFromSd(savedPath: String, savedName: String): File = File(savedPath, savedName)


}
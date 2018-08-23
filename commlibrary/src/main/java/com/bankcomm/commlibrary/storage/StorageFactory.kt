package com.bankcomm.commlibrary.storage

import java.io.File

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/22 11:08
 * anmnight@qq.com
 */
class StorageFactory : Storage {

    @Synchronized
    override fun saveToSd(sdPath: SdPath, fileName: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    @Synchronized
    override fun readFromSd(sdPath: String, fileName: String): File {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
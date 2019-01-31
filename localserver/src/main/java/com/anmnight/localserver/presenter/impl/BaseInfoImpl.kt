package com.anmnight.localserver.presenter.impl

import android.os.Handler
import android.os.HandlerThread
import com.anmnight.localserver.IServerView
import com.anmnight.localserver.LocalServerApplication
import com.anmnight.localserver.dao.BaseUserInfoDao
import com.anmnight.localserver.pojo.BaseUserInformation
import com.anmnight.localserver.presenter.IBaseInfoPresenter

class BaseInfoImpl(view: IServerView) : IBaseInfoPresenter {

    private val mDao = LocalServerApplication.getInstance().database.baseUserInfoDao()
    private val mView: IServerView = view

    private val mWorkerHandler: Handler

    init {
        view.setPresenter(this)

        val worker = HandlerThread("worker")
        worker.start()
        mWorkerHandler = Handler(worker.looper)
    }


    override fun createOrder(userName: String, phone: Long) {
        val baseUserInfo = BaseUserInformation()
        baseUserInfo.chnName = userName
        baseUserInfo.phoneNum = phone

        mWorkerHandler.post(SaveInfoRunnable(baseUserInfo, mDao))

        mView.onUserInfoCreated(baseUserInfo)
    }

    class SaveInfoRunnable(private val userInfo: BaseUserInformation, private val dao: BaseUserInfoDao) : Runnable {
        override fun run() {
            dao.insert(userInfo)
        }
    }

    override fun findOrderByPhone(phone: Long): BaseUserInformation {
        return mDao.queryByPhone(phone)
    }

}

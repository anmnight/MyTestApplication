package com.example.testapp.andserver.presenter.impl

import android.os.Handler
import android.os.HandlerThread
import com.example.testapp.TestHomeApplication
import com.example.testapp.andserver.IServerView
import com.example.testapp.andserver.database.dao.BaseUserInfoDao
import com.example.testapp.andserver.pojo.BaseUserInformation
import com.example.testapp.andserver.presenter.IBaseInfoPresenter

class BaseInfoImpl(view: IServerView) : IBaseInfoPresenter {

    private val mDao = TestHomeApplication.getInstance().database.baseUserInfoDao()
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

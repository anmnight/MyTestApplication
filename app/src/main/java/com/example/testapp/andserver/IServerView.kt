package com.example.testapp.andserver

import com.example.testapp.andserver.pojo.BaseUserInformation
import com.example.testapp.andserver.presenter.IBaseInfoPresenter

interface IServerView {
    fun onUserInfoCreated(userInfo: BaseUserInformation)
    fun setPresenter(presenter:IBaseInfoPresenter)
}

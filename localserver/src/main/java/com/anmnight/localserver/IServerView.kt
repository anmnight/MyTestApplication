package com.anmnight.localserver

import com.anmnight.localserver.pojo.BaseUserInformation
import com.anmnight.localserver.presenter.IBaseInfoPresenter

interface IServerView {
    fun onUserInfoCreated(userInfo: BaseUserInformation)
    fun setPresenter(presenter: IBaseInfoPresenter)
}

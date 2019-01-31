package com.anmnight.localserver.presenter

import com.anmnight.localserver.pojo.BaseUserInformation

interface IBaseInfoPresenter {
    fun createOrder(userName: String, phone: Long)
    fun findOrderByPhone(phone: Long): BaseUserInformation
}

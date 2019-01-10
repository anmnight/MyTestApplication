package com.example.testapp.andserver.presenter

import com.example.testapp.andserver.pojo.BaseUserInformation

interface IBaseInfoPresenter {
    fun createOrder(userName: String, phone: Long)
    fun findOrderByPhone(phone: Long): BaseUserInformation
}

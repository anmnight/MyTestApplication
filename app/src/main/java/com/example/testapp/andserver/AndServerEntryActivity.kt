package com.example.testapp.andserver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anmnight.commlibrary.widget.LoadingTextDialog
import com.example.testapp.R
import com.example.testapp.andserver.manager.ServerHostBroadcastManager
import com.example.testapp.andserver.manager.WifiUtils
import com.example.testapp.andserver.pojo.BaseUserInformation
import com.example.testapp.andserver.presenter.IBaseInfoPresenter
import com.example.testapp.andserver.presenter.impl.BaseInfoImpl
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_and_server_entry.*

class AndServerEntryActivity : AppCompatActivity(), IServerView {


    private lateinit var mServiceIntent: Intent

    private lateinit var mReceiver: ServerHostReceiver

    private lateinit var mWifiManager: WifiManager

    private lateinit var mDialog: LoadingTextDialog

    private lateinit var mPresenter: IBaseInfoPresenter

    private var mIp: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_and_server_entry)

        mServiceIntent = Intent(this, ServerHost::class.java)

        mWifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager

        mReceiver = ServerHostReceiver()

        mDialog = LoadingTextDialog(this)

        registerReceiver(mReceiver, ServerHostBroadcastManager.filter())

        BaseInfoImpl(this)

        submit.setOnClickListener { it ->

            if (userName.text.toString().isNotEmpty() && userPhone.text.toString().isNotEmpty()) {
                startService(mServiceIntent)
                mDialog.showLoading()
            } else {
                Snackbar.make(it, "姓名手机号不能为空", Snackbar.LENGTH_SHORT).show()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        stopService(mServiceIntent)
        unregisterReceiver(mReceiver)
//        WifiUtils.closeWifiHotspot(mWifiManager)

    }

    override fun onUserInfoCreated(userInfo: BaseUserInformation) {
        if (mIp != null) {
            mDialog.showText(mIp!!)
        }
    }

    override fun setPresenter(presenter: IBaseInfoPresenter) {
        mPresenter = presenter
    }

    inner class ServerHostReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            when (action) {
                ServerHostBroadcastManager.start -> {

//                    WifiUtils.createHotspot(mWifiManager)
                    mIp= intent.getStringExtra(ServerHostBroadcastManager.address)


                    val phone = userPhone.text.toString()
                    mPresenter.createOrder(userName.text.toString(), phone = phone.toLong())
                }
            }
        }
    }


}

package com.anmnight.localserver.net.api;

import android.util.Log;

import com.anmnight.localserver.LocalServerApplication;
import com.anmnight.localserver.dao.BaseUserInfoDao;
import com.anmnight.localserver.dao.SmsInfoDao;
import com.anmnight.localserver.net.JsonResponseBody;
import com.anmnight.localserver.net.ResponseJsonUnit;
import com.anmnight.localserver.pojo.BaseUserInformation;
import com.anmnight.localserver.pojo.SmsInformation;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.QueryParam;
import com.yanzhenjie.andserver.annotation.RequestBody;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RequestParam;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.http.HttpResponse;
import com.yanzhenjie.andserver.util.MediaType;

import java.util.List;


@RestController
@RequestMapping(path = "/base")
public class BaseInfoApi {

    private String TAG = "BaseInfoApi";

    private BaseUserInfoDao mUserInfoDao = LocalServerApplication.getInstance().database.baseUserInfoDao();
    private SmsInfoDao mSmsInfoDao = LocalServerApplication.getInstance().database.smsInfoDao();


    @GetMapping(path = "/info", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BaseUserInformation info(@QueryParam("phone") long phone, HttpResponse response) {

        BaseUserInformation info = mUserInfoDao.queryByPhone(phone);

        if (info == null || info.phoneNum == null) {
            response.setBody(new JsonResponseBody(ResponseJsonUnit.INSTANCE.failJson("user not found")));
        }

        return info;
    }

    @GetMapping(path = "/code")
    public String code(@QueryParam("phone") long phone, HttpResponse response) {

        String s = String.valueOf(Math.random()).substring(2, 8);
        Log.e("BaseInfoApi", "Random : " + s);
        SmsInformation info = new SmsInformation();
        info.phone = phone;
        info.content = s;
        mSmsInfoDao.insert(info);

        return "";
    }


    @GetMapping(path = "/verify")
    public String verify(@RequestParam("phone") long phone, @RequestParam("code") int code, HttpResponse response) {
        List<SmsInformation> smses = mSmsInfoDao.verify(phone, code);
        if (smses.size() > 0) {
            response.setBody(new JsonResponseBody(ResponseJsonUnit.INSTANCE.successJson("")));
        } else {
            response.setBody(new JsonResponseBody(ResponseJsonUnit.INSTANCE.failJson("")));
        }
        return "";
    }


    @PostMapping(path = "/submit")
    public boolean submit(@RequestBody BaseUserInformation userInfo) {

        mUserInfoDao.insert(userInfo);

        return true;
    }




}

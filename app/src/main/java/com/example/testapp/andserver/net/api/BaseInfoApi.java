package com.example.testapp.andserver.net.api;

import android.util.Log;

import com.example.testapp.TestHomeApplication;
import com.example.testapp.andserver.database.dao.BaseUserInfoDao;
import com.example.testapp.andserver.database.dao.SmsInfoDao;
import com.example.testapp.andserver.net.JsonResponseBody;
import com.example.testapp.andserver.net.ResponseJsonUnit;
import com.example.testapp.andserver.pojo.BaseUserInformation;
import com.example.testapp.andserver.pojo.SmsInformation;
import com.yanzhenjie.andserver.annotation.Addition;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.PostMapping;
import com.yanzhenjie.andserver.annotation.PutMapping;
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

    private BaseUserInfoDao mUserInfoDao = TestHomeApplication.getInstance().database.baseUserInfoDao();
    private SmsInfoDao mSmsInfoDao = TestHomeApplication.getInstance().database.smsInfoDao();


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

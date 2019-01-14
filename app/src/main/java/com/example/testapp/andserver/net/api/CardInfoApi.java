package com.example.testapp.andserver.net.api;

import com.anmnight.commlibrary.unit.HttpUnit;
import com.example.testapp.TestHomeApplication;
import com.example.testapp.andserver.database.dao.CardFaceDao;
import com.example.testapp.andserver.database.dao.CardInfoDao;
import com.example.testapp.andserver.pojo.CardFace;
import com.example.testapp.andserver.pojo.CardInfo;
import com.example.testapp.andserver.pojo.CardInfoHttpResponse;
import com.yanzhenjie.andserver.annotation.GetMapping;
import com.yanzhenjie.andserver.annotation.RequestMapping;
import com.yanzhenjie.andserver.annotation.RestController;
import com.yanzhenjie.andserver.util.MediaType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/card")
public class CardInfoApi {

    private CardFaceDao mCardFaceDao = TestHomeApplication.getInstance().database.cardFaceDao();
    private CardInfoDao mCardInfoDao = TestHomeApplication.getInstance().database.cardInfoDao();
    private String TAG = "CardInfoApi";

    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<CardInfoHttpResponse> loadALL() {
        List<CardInfo> infos = mCardInfoDao.findAll();
        List<CardInfoHttpResponse> respInfos = new ArrayList<>();

        for (CardInfo info : infos) {
            CardFace face = mCardFaceDao.queryByCardCode(info.getId());
            if (face != null) {
                CardInfoHttpResponse respInfo = new CardInfoHttpResponse();
                respInfo.setCardID(info.getId());
                respInfo.setCardName(info.getProductName());
                respInfo.setCardDesp(info.getCardProductInfo());
                String url = "http://" + HttpUnit.getLocalIPAddress().getHostAddress() + ":8088" + face.getSurfaceName();
                respInfo.setFacePath(url);
                respInfos.add(respInfo);
            }
        }

        return respInfos;

    }

}

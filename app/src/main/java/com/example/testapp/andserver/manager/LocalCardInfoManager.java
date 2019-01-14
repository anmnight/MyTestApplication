package com.example.testapp.andserver.manager;

import android.content.Context;

import com.example.testapp.TestHomeApplication;
import com.example.testapp.andserver.database.dao.CardFaceDao;
import com.example.testapp.andserver.database.dao.CardInfoDao;
import com.example.testapp.andserver.pojo.CardFace;
import com.example.testapp.andserver.pojo.CardInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LocalCardInfoManager {

    private String TAG = "LocalCardInfoManager";

    private Context mCtx;

    private CardInfoDao mCardInfoDao = TestHomeApplication.getInstance().database.cardInfoDao();
    private CardFaceDao mCardFaceDao = TestHomeApplication.getInstance().database.cardFaceDao();

    public LocalCardInfoManager(Context context) {
        this.mCtx = context;
    }

    public void readInfo() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mCtx.getAssets().open("cardinfo.txt")));
        while (bufferedReader.read() != -1) {
            String string = bufferedReader.readLine();
            mCardInfoDao.insert(converterCardInfo(string));
        }
    }


    public void readFace() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(mCtx.getAssets().open("cardface.txt")));

        while (bufferedReader.read() != -1) {

            String string = bufferedReader.readLine();
            mCardFaceDao.insert(converterCardFace(string));

        }

    }


    private CardFace converterCardFace(String input) {
        CardFace face = new CardFace();
        String[] data = input.split("\\|");
        face.setId(data[0]);
        face.setState(FormatUnit.ConverterInt(data[1]) == 1);
        face.setUpdateTime(data[2]);
        face.setCardCode(data[3]);
        face.setCardProducts(data[4]);
        face.setZuCard(data[5]);
        face.setCardRank(data[6]);
        face.setFaceCode(data[7]);
        face.setSurfaceName(data[8]);
        face.setCardPrice(FormatUnit.ConverterFloat(data[9]));

        return face;
    }

    private CardInfo converterCardInfo(String input) {
        CardInfo info = new CardInfo();
        String[] data = input.split("\\|");
        info.setId(data[0]);
        info.setState(FormatUnit.ConverterInt(data[1]) != 0);
        info.setUpdateTime(data[2]);
        info.setProductSN(FormatUnit.ConverterInt(data[3]));
        info.setProductName(data[4]);
        info.setOrganizationSN(data[5]);
        info.setGoldMark(data[6]);
        info.setBusinessType(data[7]);
        info.setOriginId(data[8]);
        info.setCityId(data[9]);
        info.setUpGrade(data[10]);
        info.setVisaEmv(FormatUnit.ConverterInt(data[11]) != 0);
        info.setMasterEmv(FormatUnit.ConverterInt(data[12]) != 0);
        info.setEmvUseCity(data[13]);
        info.setProductSort(FormatUnit.ConverterInt(data[14]));
        info.setSeconds(FormatUnit.ConverterInt(data[15]) == 1);
        info.setApplyDebit(FormatUnit.ConverterInt(data[16]) != 0);
        info.setCardShowType(data[17]);
        info.setYouthProcess(FormatUnit.ConverterInt(data[18]) != 0);
        info.setCampusProcess(FormatUnit.ConverterInt(data[19]) != 0);
        info.setCardAgeLimit(FormatUnit.ConverterInt(data[20]));
        info.setCardProductCategory(data[21]);
        info.setCardProductInfo(data[22]);
        return info;
    }


}

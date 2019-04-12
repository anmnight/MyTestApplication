package com.example.testapp.design.结构设计模式.facade;

public class ZhangWuJi {

    private ZhaoShi mZhaoShi;
    private JinMai mJinMai;
    private NeiGong mNeiGong;

    public ZhangWuJi() {
        this.mZhaoShi = new ZhaoShi();
        this.mJinMai = new JinMai();
        this.mNeiGong = new NeiGong();
    }


    public void QianKun() {
        mJinMai.jingmai();
        mNeiGong.QianKun();
    }


    public void QiShang() {
        mJinMai.jingmai();
        mNeiGong.JiuYang();
        mZhaoShi.QiShangQuan();
    }
}

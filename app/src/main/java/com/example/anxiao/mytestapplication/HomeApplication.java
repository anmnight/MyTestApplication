package com.example.anxiao.mytestapplication;

import android.app.Application;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

public class HomeApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Logger.DEBUG("app starting..");

        SophixManager.getInstance()
                .setContext(this)
                .setAppVersion("1.1")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {

                        Logger.DEBUG(code);
                        Logger.DEBUG(info);
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Logger.DEBUG("load successs");

                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Logger.DEBUG("restart application");

                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                            Logger.DEBUG("clear cache");
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Logger.DEBUG("error");
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();
    }
}

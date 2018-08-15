package com.bankcomm.commlibrary;

import java.util.List;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/15 09:02
 * anmnight@qq.com
 */
public class VersionResult {


    /**
     * status : true
     * message : 查询成功
     * data : [{"appId":"com.anmnight.rooms","versionNo":1,"url":"","necessary":true}]
     */

    private boolean status;
    private String message;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * appId : com.anmnight.rooms
         * versionNo : 1
         * url :
         * necessary : true
         */

        private String appId;
        private int versionNo;
        private String url;
        private boolean necessary;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public int getVersionNo() {
            return versionNo;
        }

        public void setVersionNo(int versionNo) {
            this.versionNo = versionNo;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isNecessary() {
            return necessary;
        }

        public void setNecessary(boolean necessary) {
            this.necessary = necessary;
        }
    }
}

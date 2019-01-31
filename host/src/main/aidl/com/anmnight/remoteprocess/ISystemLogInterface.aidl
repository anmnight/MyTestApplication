// ISystemLogInterface.aidl
package com.anmnight.remoteprocess;

import com.anmnight.remoteprocess.pojo.SystemLog;

interface ISystemLogInterface {

    void startSystemLog();

    void stopSystemLog();

    SystemLog loadSystemLogNow();

    void loadSpaceSystemLog(int start,int end);

}

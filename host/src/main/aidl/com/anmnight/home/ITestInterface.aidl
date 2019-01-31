// ITestInterface.aidl
package com.anmnight.home;

import com.anmnight.home.pojos.Version;

interface ITestInterface {

    void heartPack(long seed);

    Version version(String appId);


}

// IMyAidlInterface.aidl
package com.example.testapp.lesson_android_aidl;
// Declare any non-default types here with import statements
import com.example.testapp.lesson_android_aidl.ITestAction;

interface IService {
    void registerTestAction(ITestAction action);
    void unRegisterTestAction(ITestAction action);
}

package com.example.testapp.homeinns.rooms.pojo;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/6/1 19:25
 * anmnight@qq.com
 */
public class UserBean {
    /**
     * IsLoginFirst : false
     * AuthType : 1
     * AuthToken : OURDQjdDQjA3QkVDQkI5QjkxMkU3Mzg2MDlDMjE4QkNFOEU0MkM2MkZBRkZFNzE1NjNBNTU2NUZCQ0Q2RDk1Rg==
     * AuthenticationType : Basic
     * IsAuthenticated : true
     * Name :
     * SystemUserId : 13628882460
     * UserName :
     * UserCode : 13628882460
     * FriendlyName : null
     */

    private boolean IsLoginFirst;
    private int AuthType;
    private String AuthToken;
    private String AuthenticationType;
    private boolean IsAuthenticated;
    private String Name;
    private String SystemUserId;
    private String UserName;
    private String UserCode;
    private Object FriendlyName;

    public boolean isIsLoginFirst() {
        return IsLoginFirst;
    }

    public void setIsLoginFirst(boolean IsLoginFirst) {
        this.IsLoginFirst = IsLoginFirst;
    }

    public int getAuthType() {
        return AuthType;
    }

    public void setAuthType(int AuthType) {
        this.AuthType = AuthType;
    }

    public String getAuthToken() {
        return AuthToken;
    }

    public void setAuthToken(String AuthToken) {
        this.AuthToken = AuthToken;
    }

    public String getAuthenticationType() {
        return AuthenticationType;
    }

    public void setAuthenticationType(String AuthenticationType) {
        this.AuthenticationType = AuthenticationType;
    }

    public boolean isIsAuthenticated() {
        return IsAuthenticated;
    }

    public void setIsAuthenticated(boolean IsAuthenticated) {
        this.IsAuthenticated = IsAuthenticated;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSystemUserId() {
        return SystemUserId;
    }

    public void setSystemUserId(String SystemUserId) {
        this.SystemUserId = SystemUserId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getUserCode() {
        return UserCode;
    }

    public void setUserCode(String UserCode) {
        this.UserCode = UserCode;
    }

    public Object getFriendlyName() {
        return FriendlyName;
    }

    public void setFriendlyName(Object FriendlyName) {
        this.FriendlyName = FriendlyName;
    }

}

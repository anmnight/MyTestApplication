package http.bean;

/**
 * Created by anxiao on 2017/8/1.
 */

public class UserResponseBean {

    /**
     * IsLoginFirst : false
     * AuthType : 1
     * AuthToken : NTFGQjMwN0IxNDhBQjAyOUFGRjI5QUI5NkFCMjg0MDJFNkQ0MDU5Mjg4MDE0ODFGOTY3Q0UwMjg2OTlENjBBMQ==
     * AuthenticationType : Basic
     * IsAuthenticated : true
     * Name :
     * SystemUserId : 18502938991
     * UserName :
     * UserCode : 18502938991
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

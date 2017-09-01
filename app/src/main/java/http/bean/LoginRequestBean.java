package http.bean;

/**
 * Created by anxiao on 2017/8/1.
 */

public class LoginRequestBean {

    private String UserAccountName;
    private String Password;

    public String getUserAccountName() {
        return UserAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        UserAccountName = userAccountName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}

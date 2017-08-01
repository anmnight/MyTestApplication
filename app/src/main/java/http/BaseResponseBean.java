package http;

/**
 * Created by anxiao on 2017/8/1.
 */

public class BaseResponseBean<T> {


    /**
     * code : OK
     * data : {"_id":"string","birthday":"2017-08-01T07:37:31.526Z","buyer_level":"string","code":"string","comment_count":0,"description":"string","email":"string","gender":"string","header_img":"string","is_imported_im":true,"is_remind_msg":true,"is_remind_msg_goods":true,"is_remind_new_order":true,"last_login_time":"2017-08-01T07:37:31.527Z","level":"string","lock_status":0,"login_count":0,"login_from":0,"name":"string","phone":"string","seller_level":"string","shop_id":"string","shop_name":"string","token":"string","total_buy":0,"total_sell":0,"wx_city":"string","wx_contry":"string","wx_headimgurl":"string","wx_nickname":"string","wx_province":"string","wx_sex":"string"}
     * exception : string
     * message : string
     * path : string
     */

    private String code;
    private String exception;
    private String message;
    private String path;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

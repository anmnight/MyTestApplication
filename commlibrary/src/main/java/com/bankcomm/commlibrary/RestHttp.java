package com.bankcomm.commlibrary;

import com.bankcomm.commlibrary.http.RequestParams;
import com.bankcomm.commlibrary.http.Result;

/**
 * https://github.com/anmnight
 * author：anxiao on 2018/8/13 10:31
 * anmnight@qq.com
 */
public interface RestHttp {
    /**
     * get 请求
     *
     * @param params 请求参数
     * @param <T>    返回类型
     * @return 返回结果
     */
    <T> Result<T> getRequest(RequestParams params);

    /**
     * post 请求
     *
     * @param params 请求参数
     * @param <T>    返回类型
     * @return 返回结果
     */
    <T> Result<T> postRequest(RequestParams params);

    /**
     * 文件下载 请求
     *
     * @param params 请求参数
     * @param <T>    返回类型
     * @return 返回结果
     */
    <T> Result<T> fileDownloadRequest(RequestParams params);

    /**
     * 文件上传 请求
     *
     * @param params 请求参数
     * @param <T>    返回类型
     * @return 返回结果
     */
    <T> Result<T> fileUploadRequest(RequestParams params);

}

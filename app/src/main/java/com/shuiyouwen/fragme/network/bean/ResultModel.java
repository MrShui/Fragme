package com.shuiyouwen.fragme.network.bean;

/**
 * Created by Shui on 16/11/6.
 */

public class ResultModel<T> {
    public final static String REQUEST_SUCCESS = "0";

    private String errcode;
    private String errmsg;
    private T data;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

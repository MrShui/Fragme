package com.iuicity.xinjr.network.exception;

/**
 * 服务器异常
 * Created by Administrator on 2016/11/8 0008.
 */
public class ServerException extends Exception {
    private int code;
    private String msg;

    public ServerException(String msg,int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

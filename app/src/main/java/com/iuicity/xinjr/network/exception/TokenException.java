package com.iuicity.xinjr.network.exception;

/**
 * token失效异常
 * Created by Administrator on 2016/11/8 0008.
 */

public class TokenException extends Exception {
    private String errorMsg;
    private int errorCode;

    public TokenException(String errorMsg, int errorCode) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}

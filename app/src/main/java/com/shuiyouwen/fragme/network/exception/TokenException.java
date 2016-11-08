package com.shuiyouwen.fragme.network.exception;

/**
 * token失效异常
 * Created by Administrator on 2016/11/8 0008.
 */

public class TokenException extends Exception {
    public TokenException(String errorMsg) {
        super(errorMsg);
    }

}

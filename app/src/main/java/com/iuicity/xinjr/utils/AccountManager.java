package com.iuicity.xinjr.utils;

import com.iuicity.xinjr.network.bean.resp.LoginResp;

/**
 * 账户管理相关
 * Created by Shui on 2017/7/5.
 */

public class AccountManager {
    private static AccountManager mInstance;
    private LoginResp mLoginResp;


    public static AccountManager getInstance() {
        if (mInstance == null) {
            mInstance = new AccountManager();
        }
        return mInstance;
    }

    public void setUserInfo(LoginResp userInfo) {
        mLoginResp = userInfo;
    }

    public LoginResp getUserInfo() {
        return mLoginResp;
    }

}

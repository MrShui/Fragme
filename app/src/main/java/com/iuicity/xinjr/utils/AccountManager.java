package com.iuicity.xinjr.utils;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import com.iuicity.xinjr.network.bean.resp.LoginResp;

/**
 * 账户管理相关
 * Created by Shui on 2017/7/5.
 */

public class AccountManager {
    private final static String SAVE_INSTANCE = "save_instance";
    private static AccountManager mInstance;
    private LoginResp mLoginResp;
    private boolean mTestFlag;
    private String mTestString;

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

    /**
     * activity快要被杀死时，保存数据
     * @param bundle
     */
    public void onSaveInstance(Bundle bundle) {
        Data data = new Data();
        data.setLoginResp(mLoginResp);
        data.setTestFlag(mTestFlag);
        data.setTestString(mTestString);
        bundle.putParcelable(SAVE_INSTANCE, data);
    }

    /**
     * 重新进入activity取出保存的数据
     */
    public void getSaveInstance(Bundle bundle) {
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable(SAVE_INSTANCE);
            if (parcelable != null) {
                Data data = (Data) parcelable;
                mLoginResp = data.getLoginResp();
                mTestFlag = data.isTestFlag();
                mTestString = data.getTestString();
            }
        }
    }

    private class Data implements Parcelable {
        private LoginResp mLoginResp;
        private boolean testFlag;
        private String testString;

        public LoginResp getLoginResp() {
            return mLoginResp;
        }

        void setLoginResp(LoginResp loginResp) {
            mLoginResp = loginResp;
        }

        public boolean isTestFlag() {
            return testFlag;
        }

        void setTestFlag(boolean testFlag) {
            this.testFlag = testFlag;
        }

        public String getTestString() {
            return testString;
        }

        void setTestString(String testString) {
            this.testString = testString;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(this.mLoginResp, flags);
            dest.writeByte(this.testFlag ? (byte) 1 : (byte) 0);
            dest.writeString(this.testString);
        }

        Data() {
        }

        Data(Parcel in) {
            this.mLoginResp = in.readParcelable(LoginResp.class.getClassLoader());
            this.testFlag = in.readByte() != 0;
            this.testString = in.readString();
        }

        public final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
            @Override
            public Data createFromParcel(Parcel source) {
                return new Data(source);
            }

            @Override
            public Data[] newArray(int size) {
                return new Data[size];
            }
        };
    }
}

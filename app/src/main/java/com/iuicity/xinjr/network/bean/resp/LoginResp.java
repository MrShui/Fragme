package com.iuicity.xinjr.network.bean.resp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author: luosheng
 * @time: 2017/3/28 0028 12:44
 * @desc: <类的描述>
 */

public class LoginResp implements Parcelable {


    /**
     * token : c200cb551e1aaa9b51898a09c6c226db
     * uid : 42
     * type : 1
     * status : 0
     */

    private String token;
    private String uid;
    private String type;
    private String status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.token);
        dest.writeString(this.uid);
        dest.writeString(this.type);
        dest.writeString(this.status);
    }

    public LoginResp() {
    }

    protected LoginResp(Parcel in) {
        this.token = in.readString();
        this.uid = in.readString();
        this.type = in.readString();
        this.status = in.readString();
    }

    public static final Parcelable.Creator<LoginResp> CREATOR = new Parcelable.Creator<LoginResp>() {
        @Override
        public LoginResp createFromParcel(Parcel source) {
            return new LoginResp(source);
        }

        @Override
        public LoginResp[] newArray(int size) {
            return new LoginResp[size];
        }
    };
}

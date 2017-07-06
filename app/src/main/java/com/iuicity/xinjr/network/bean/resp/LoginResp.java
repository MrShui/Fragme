package com.iuicity.xinjr.network.bean.resp;

/**
 * @author: luosheng
 * @time: 2017/3/28 0028 12:44
 * @desc: <类的描述>
 */

public class LoginResp {


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
}

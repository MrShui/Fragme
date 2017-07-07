package com.iuicity.xinjr.network.api

import com.google.gson.Gson
import com.iuicity.xinjr.network.bean.ResultModel
import com.iuicity.xinjr.network.bean.resp.LoginResp

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.mock.BehaviorDelegate

/**
 * 模拟接口
 * Created by Mc丶水水 on 2017/3/31 0031.
 */

class MockApi(private val mDelegate: BehaviorDelegate<Api>) : Api {
    private val mGson = Gson()

    override fun getPubkey(@Field("equipment") equipment: String): Observable<ResultModel<String>> {
        val target = ResultModel<String>()
        target.code = 0
        target.data = "asdasdhaksdhkah"
        target.msg = "获取公钥出错"
        return mDelegate.returningResponse(target).getPubkey("")
    }

    override fun login(@Field("equipment") equipment: String, @Field("encrypt") encrypt: String, @Field("version") version: String, @Field("platform") platform: String, @Field("from") from: String): Observable<ResultModel<LoginResp>> {
        val target = ResultModel<LoginResp>()
        target.code = 0
        val loginResp = LoginResp()
        loginResp.status = "state"
        loginResp.token = "token"
        loginResp.type = "type"
        loginResp.uid = "uid"
        target.data = loginResp
        target.msg = "登录出错"
        return mDelegate.returningResponse(target).login("", "", "", "", "")
    }
}

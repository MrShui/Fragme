package com.iuicity.xinjr.network.api

import com.iuicity.xinjr.network.bean.ResultModel
import com.iuicity.xinjr.network.bean.resp.LoginResp

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 真实接口请求
 * Created by Shui on 16/10/28.
 */

interface Api {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("api/Loading/sendKey")
    fun getPubkey(@Field("equipment") equipment: String): Observable<ResultModel<String>>

    /**
     * 登录

     * @return
     */
    @FormUrlEncoded
    @POST("api/login/loginV1_2")
    fun login(@Field("equipment") equipment: String, @Field("encrypt") encrypt: String,
              @Field("version") version: String, @Field("platform") platform: String,
              @Field("from") from: String): Observable<ResultModel<LoginResp>>
}

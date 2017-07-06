package com.iuicity.xinjr.network.api;

import com.iuicity.xinjr.network.bean.ResultModel;
import com.iuicity.xinjr.network.bean.resp.LoginResp;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * 真实接口请求
 * Created by Shui on 16/10/28.
 */

public interface Api {
    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("api/Loading/sendKey")
    Observable<ResultModel<String>> getPubkey(@Field("equipment") String equipment);

    /**
     * 登录
     *
     * @return
     */
    @FormUrlEncoded
    @POST("api/login/loginV1_2")
    Observable<ResultModel<LoginResp>> login(@Field("equipment") String equipment, @Field("encrypt") String encrypt,
                                             @Field("version") String version, @Field("platform") String platform,
                                             @Field("from") String from);
}

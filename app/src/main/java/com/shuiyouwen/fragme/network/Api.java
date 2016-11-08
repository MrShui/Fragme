package com.shuiyouwen.fragme.network;


import com.shuiyouwen.fragme.network.bean.ResultModel;
import com.shuiyouwen.fragme.network.bean.resp.LoadingResp;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Shui on 16/10/28.
 */

public interface Api {
    @GET("guide-page")
    Observable<ResultModel<List<LoadingResp>>> loading();
}

package com.iuicity.xinjr.network.api;

import com.google.gson.Gson;

import retrofit2.mock.BehaviorDelegate;

/**
 * Created by Mc丶水水 on 2017/3/31 0031.
 */

public class MockApi {
    private final BehaviorDelegate<Api> mDelegate;
    private Gson mGson = new Gson();

    public MockApi(BehaviorDelegate<Api> delegate) {
        mDelegate = delegate;
    }

    //    @Override
//    public Observable<ResultModel<List<LoadingResp>>> loading() {
//        String mockJson = "{\"errcode\":2,\"errmsg\":\"5555，服务器出错了\",\"data\":[]}";
//        ResultModel<List<LoadingResp>> response = mGson.fromJson(mockJson, ResultModel.class);
//        return mDelegate.returningResponse(response).loading();
//    }
}

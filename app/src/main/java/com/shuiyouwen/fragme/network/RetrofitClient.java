package com.shuiyouwen.fragme.network;

import com.shuiyouwen.common_lib.L;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Shui on 16/10/28.
 */

public class RetrofitClient {
    private static Api mApi;

    public static Api getApi() {
        if (mApi == null) {
            String baseUrl = UrlConfiguration.getInstance().getBaseUrl();
            HttpUrl url = HttpUrl.parse(baseUrl);
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(60, TimeUnit.SECONDS);
            builder.readTimeout(60, TimeUnit.SECONDS);
            if (L.isDebug) {
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.addInterceptor(loggingInterceptor);
            }
            retrofit2.Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl(url)
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            mApi = retrofit.create(Api.class);
        }
        return mApi;
    }
}

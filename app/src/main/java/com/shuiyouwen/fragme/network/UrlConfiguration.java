package com.shuiyouwen.fragme.network;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取请求地址配置
 * Created by Shui on 16/11/6.
 */

public class UrlConfiguration {
    private static UrlConfiguration mInstance;
    private static Properties mProperties;

    private UrlConfiguration() {
    }

    public static void init(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("config/url.properties");
            mProperties = new Properties();
            mProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static UrlConfiguration getInstance() {
        if (mInstance == null) {
            mInstance = new UrlConfiguration();
        }
        return mInstance;
    }

    public String getBaseUrl() {
        String baseUrl = mProperties.getProperty("baseUrl");
        if (TextUtils.isEmpty(baseUrl)) {
            throw new NullPointerException("获取请求基本地址失败！");
        } else {
            return baseUrl;
        }
    }
}

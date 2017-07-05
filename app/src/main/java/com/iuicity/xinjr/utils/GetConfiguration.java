package com.iuicity.xinjr.utils;

import android.content.Context;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取配置
 * Created by Shui on 16/11/6.
 */

public class GetConfiguration {
    private static GetConfiguration mInstance;
    private static Properties mProperties;

    private GetConfiguration() {
    }

    public static void init(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("config/config.properties");
            mProperties = new Properties();
            mProperties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GetConfiguration getInstance() {
        if (mInstance == null) {
            mInstance = new GetConfiguration();
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

    public String getUploadPictureFlag() {
        String uploadPicture = mProperties.getProperty("uploadPicture");
        if (TextUtils.isEmpty(uploadPicture)) {
            throw new NullPointerException("获取请求基本地址失败！");
        } else {
            return uploadPicture;
        }
    }
}

package com.iuicity.xinjr;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.iuicity.xinjr.utils.GetConfiguration;

/**
 * Created by Shui on 2017/7/5.
 */

public class App extends Application {
    @SuppressLint("StaticFieldLeak")
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        GetConfiguration.init(this);
        sContext = getApplicationContext();
    }

}

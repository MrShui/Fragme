package com.iuicity.xinjr;

import android.app.Application;

import com.iuicity.xinjr.utils.GetConfiguration;

/**
 * Created by Shui on 2017/7/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        GetConfiguration.init(this);
    }
}

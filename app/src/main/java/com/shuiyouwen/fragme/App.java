package com.shuiyouwen.fragme;

import android.app.Application;
import android.content.Context;

import com.shuiyouwen.fragme.network.UrlConfiguration;

/**
 * Created by Shui on 16/11/6.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UrlConfiguration.init(this);
    }

}

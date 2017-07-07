package com.iuicity.xinjr

import android.app.Application
import android.content.Context
import com.iuicity.xinjr.utils.GetConfiguration
import com.squareup.leakcanary.LeakCanary

/**
 * Created by Shui on 2017/7/5.
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)
        GetConfiguration.init(this)
        sContext = applicationContext
    }

    companion object {
        lateinit var sContext: Context
    }

}

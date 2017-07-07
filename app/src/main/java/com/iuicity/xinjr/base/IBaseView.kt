package com.iuicity.xinjr.base

import android.support.annotation.CheckResult

import com.trello.rxlifecycle2.LifecycleTransformer

/**
 * View的基类
 * Created by Shui on 2017/7/5.
 */

interface IBaseView {
    fun openLoading() //启动网络请求进度提示

    fun closeLoading() //关闭网络请求进度提示

    fun showError(errorMsg: String) //显示错误提示

    fun launchLogin(msg: String) //启动登录界面

    fun netError(e: Throwable) //网络错误

    fun loginBack() //登录之后回来

    @CheckResult
    fun <T> bindToLifecycle(): LifecycleTransformer<T> //rxjava绑定view生命周期
}

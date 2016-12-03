package com.shuiyouwen.fragme.network;

import android.content.Intent;

import com.shuiyouwen.fragme.base.BaseActivity;
import com.shuiyouwen.fragme.base.BaseFragment;
import com.shuiyouwen.fragme.feature.module1.NetErrorActivity;
import com.shuiyouwen.fragme.network.exception.ServerException;
import com.shuiyouwen.fragme.network.exception.TokenException;
import com.shuiyouwen.fragme.base.IBaseView;

import rx.Subscriber;

/**
 * 网络请求返回结果的处理的基本封装
 * Created by Shui on 16/10/28.
 */

public abstract class ResultSubscriber<T> extends Subscriber<T> {

    private final IBaseView mBaseView;
    private final String mMethodName;

    public ResultSubscriber(IBaseView baseView, String methodName) {
        mBaseView = baseView;
        mMethodName = methodName;

        mBaseView.openLoading();
    }

    @Override
    public void onCompleted() {
        mBaseView.closeLoading();
    }

    @Override
    public void onError(Throwable e) {
        mBaseView.closeLoading();
        if (e instanceof ServerException) {
            //服务器异常
            mBaseView.showError(e.getMessage());
        } else if (e instanceof TokenException) {
            //登录状态失效
            mBaseView.showError(e.getMessage());
        } else {
            //其它异常（json解析问题、网络异常等等）
            if (mBaseView instanceof BaseActivity) {
                BaseActivity baseActivity = (BaseActivity) mBaseView;
                Intent intent = new Intent(baseActivity, NetErrorActivity.class);
                intent.putExtra(NetErrorActivity.METHOD_NAME, mMethodName);
                baseActivity.startActivityForResult(intent, BaseActivity.NET_ERROR_REQ);
            } else if (mBaseView instanceof BaseFragment) {

            }
        }
    }

    @Override
    public abstract void onNext(T t);
}

package com.shuiyouwen.fragme.network;

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

    public ResultSubscriber(IBaseView baseView) {
        mBaseView = baseView;
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
            mBaseView.showError("网络不给力");
        }
    }

    @Override
    public abstract void onNext(T t);
}

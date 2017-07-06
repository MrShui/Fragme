package com.iuicity.xinjr.feature.login;

import com.hwangjr.rxbus.RxBus;
import com.iuicity.xinjr.constants.TagConstants;
import com.iuicity.xinjr.network.ResultSubject;
import com.iuicity.xinjr.network.RetrofitClient;
import com.iuicity.xinjr.network.bean.ResultModel;
import com.iuicity.xinjr.network.bean.resp.LoginResp;
import com.iuicity.xinjr.network.helper.RxResultHelper;
import com.iuicity.xinjr.network.helper.RxSchedulersHelper;
import com.iuicity.xinjr.utils.AccountManager;

import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Shui on 2017/7/5.
 */

public class LoginPresenter implements LoginConstract.Presenter {

    private final LoginConstract.View mView;

    public LoginPresenter(LoginConstract.View view) {
        mView = view;
    }


    @Override
    public void login(String account, String pwd) {
        RetrofitClient.getMockApi().getPubkey("")
                .compose(RxSchedulersHelper.<ResultModel<String>>ioMain())
                .compose(RxResultHelper.<String>handleResult())
                .compose(mView.<String>bindToLifecycle())
                .flatMap(new Function<String, ObservableSource<ResultModel<LoginResp>>>() {
                    @Override
                    public ObservableSource<ResultModel<LoginResp>> apply(@NonNull String s) throws Exception {
                        return RetrofitClient.getMockApi().login("", "", "", "", "");
                    }
                })
                .compose(RxResultHelper.<LoginResp>handleResult())
                .subscribe(new ResultSubject<LoginResp>(mView) {
                    @Override
                    public void onNext(LoginResp loginResp) {
                        AccountManager.getInstance().setUserInfo(loginResp);
                        mView.gotoMain();
                        RxBus.get().post(TagConstants.LOGIN_BACK, "登录成功");
                    }
                });
    }
}

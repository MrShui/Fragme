package com.iuicity.xinjr.feature.login

import com.iuicity.xinjr.network.ResultSubject
import com.iuicity.xinjr.network.RetrofitClient
import com.iuicity.xinjr.network.bean.ResultModel
import com.iuicity.xinjr.network.bean.resp.LoginResp
import com.iuicity.xinjr.network.helper.RxResultHelper
import com.iuicity.xinjr.network.helper.RxSchedulersHelper
import com.iuicity.xinjr.utils.AccountManager

/**
 * Created by Shui on 2017/7/5.
 */

class LoginPresenter(private val mView: LoginConstract.View) : LoginConstract.Presenter {


    override fun login(account: String, pwd: String) {
        RetrofitClient.getMockApi().getPubkey("")
                .compose(RxSchedulersHelper.ioMain<ResultModel<String>>())
                .compose(RxResultHelper.handleResult<String>())
                .compose(mView.bindToLifecycle<String>())
                .flatMap { RetrofitClient.getMockApi().login("", "", "", "", "") }
                .compose(RxResultHelper.handleResult<LoginResp>())
                .subscribe(object : ResultSubject<LoginResp>(mView) {
                    override fun onNext(loginResp: LoginResp) {
                        AccountManager.get().userInfo = loginResp
                        mView.gotoMain()
                    }
                })
    }
}

package com.shuiyouwen.fragme.base;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public interface IBaseView {
    void openLoading();

    void closeLoading();

    void showError(String errorMsg);

    void launchLogin();
}

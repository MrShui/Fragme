package com.shuiyouwen.fragme.feature.module1;

import android.os.Bundle;
import android.widget.Button;

import com.shuiyouwen.common_lib.L;
import com.shuiyouwen.fragme.R;
import com.shuiyouwen.fragme.base.BaseActivity;
import com.shuiyouwen.fragme.network.ResultSubscriber;
import com.shuiyouwen.fragme.network.RetrofitClient;
import com.shuiyouwen.fragme.network.bean.ResultModel;
import com.shuiyouwen.fragme.network.bean.resp.LoadingResp;
import com.shuiyouwen.fragme.network.helper.RxResultHelper;
import com.shuiyouwen.fragme.network.helper.RxSchedulersHelper;
import com.trello.rxlifecycle.RxLifecycle;
import com.trello.rxlifecycle.android.RxLifecycleAndroid;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_click)
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_click)
    public void onClick() {
        RetrofitClient.getApi().loading()
                .compose(RxSchedulersHelper.<ResultModel<List<LoadingResp>>>ioMain())
                .compose(RxResultHelper.<List<LoadingResp>>handleResult())
                .compose(this.<List<LoadingResp>>bindToLifecycle())
                .subscribe(new ResultSubscriber<List<LoadingResp>>(this) {
                    @Override
                    public void onNext(List<LoadingResp> loadingResps) {
                        L.i("日志：" + loadingResps.toString());
                    }
                });
    }
}

package com.iuicity.xinjr.feature.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Shui on 2017/7/5.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.btn_go_main)
    Button mBtnGoMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @Override
    public void loginBack() {
        Toast.makeText(this, "登录回来刷新界面", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_go_main)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_go_main:
                launchLogin("");
                break;
        }
    }
}

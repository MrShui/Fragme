package com.iuicity.xinjr.feature.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseActivity;
import com.iuicity.xinjr.constants.C;
import com.iuicity.xinjr.feature.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginActivity extends BaseActivity implements LoginConstract.View {
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;

    public final static int RESULT_LOGIN_SUCCESS = 1002;
    private Unbinder mBind;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mBind = ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }

    @OnClick(R.id.btn_login)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if ((TextUtils.isEmpty(mEtAccount.getText()))) {
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if ((TextUtils.isEmpty(mEtPwd.getText()))) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.login(mEtAccount.getText().toString(), mEtPwd.getText().toString());
                }
                break;
        }
    }

    @Override
    public void gotoMain() {
        int req_login = getIntent().getIntExtra(C.REQ_LOGIN_KEY, 0);
        if (req_login == C.REQ_LOGIN) {
            //从别的界面跳转到登录
            setResult(RESULT_LOGIN_SUCCESS);
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        finish();
    }
}

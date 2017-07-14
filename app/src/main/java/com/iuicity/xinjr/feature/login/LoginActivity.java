package com.iuicity.xinjr.feature.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseActivity;
import com.iuicity.xinjr.constants.C;
import com.iuicity.xinjr.feature.main.MainActivity;
import com.iuicity.xinjr.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements LoginConstract.View {
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.fl_cancel)
    FrameLayout mFlCancel;

    public final static int RESULT_LOGIN_SUCCESS = 1002;
    private Unbinder mBind;
    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mBind = ButterKnife.bind(this);
        StatusBarUtil.stateBarSetting(this, mFlCancel);
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
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

    @OnClick({R.id.fl_cancel, R.id.tv_forget_pwd, R.id.btn_login, R.id.tv_regist_immediately})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_cancel:
                finish();
                break;
            case R.id.tv_forget_pwd:
                Intent intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                if ((TextUtils.isEmpty(mEtAccount.getText()))) {
                    Toast.makeText(this, "账号不能为空", Toast.LENGTH_SHORT).show();
                } else if ((TextUtils.isEmpty(mEtPwd.getText()))) {
                    Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.login(mEtAccount.getText().toString(), mEtPwd.getText().toString());
                }
                break;
            case R.id.tv_regist_immediately:
                break;
        }
    }
}

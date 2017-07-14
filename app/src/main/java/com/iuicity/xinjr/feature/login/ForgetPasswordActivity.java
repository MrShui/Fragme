package com.iuicity.xinjr.feature.login;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseActivity;
import com.iuicity.xinjr.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 忘记密码
 * Created by Shui on 2017/7/14.
 */

public class ForgetPasswordActivity extends BaseActivity {
    @BindView(R.id.fl_cancel)
    FrameLayout mFlCancel;
    @BindView(R.id.et_account)
    EditText mEtAccount;
    @BindView(R.id.et_verify_code)
    EditText mEtVerifyCode;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.iv_eye)
    ImageView mIvEye;

    private int mPasswordInvisible = InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT;
    private int mPassWordVisible = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD | InputType.TYPE_CLASS_TEXT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        StatusBarUtil.stateBarSetting(this, mFlCancel);
        initView();
    }

    private void initView() {
        //默认密码不可见
        mEtPwd.setInputType(mPasswordInvisible);
        mIvEye.setImageResource(R.mipmap.eye);
    }

    @OnClick({R.id.fl_cancel, R.id.btn_verify_code, R.id.btn_reset_password, R.id.iv_eye})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fl_cancel:
                finish();
                break;
            case R.id.btn_verify_code:
                break;
            case R.id.btn_reset_password:

                break;
            case R.id.iv_eye:
                togglePwdHint();
                break;
        }
    }

    /**
     * 切换密码框的显示隐藏
     */
    private void togglePwdHint() {
        int lastType = mEtPwd.getInputType();
        mEtPwd.setInputType(lastType == mPasswordInvisible ? mPassWordVisible : mPasswordInvisible);
        @DrawableRes int imgRes = lastType == mPasswordInvisible ? R.mipmap.eye1 : R.mipmap.eye;
        mIvEye.setImageResource(imgRes);
    }
}

package com.iuicity.xinjr.feature.login

import android.content.Intent
import android.os.Bundle
import com.iuicity.xinjr.R
import com.iuicity.xinjr.base.BaseActivity
import com.iuicity.xinjr.constants.C
import com.iuicity.xinjr.feature.main.MainActivity
import com.iuicity.xinjr.utils.isEmpty
import com.iuicity.xinjr.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginConstract.View {
    private val mPresenter: LoginPresenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btn_login.setOnClickListener {
            if (isEmpty(et_account.text)) {
                toast("账号不能为空")
            } else if (isEmpty(et_pwd.text)) {
                toast("密码不能为空")
            } else {
                mPresenter.login(et_account.text.toString(), et_pwd.text.toString())
            }
        }
    }

    override fun gotoMain() {
        val req_login = intent.getIntExtra(C.REQ_LOGIN_KEY, 0)
        if (req_login == C.REQ_LOGIN) {
            //从别的界面跳转到登录
            setResult(RESULT_LOGIN_SUCCESS)
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        finish()
    }

    companion object {
        val RESULT_LOGIN_SUCCESS = 1002
    }
}

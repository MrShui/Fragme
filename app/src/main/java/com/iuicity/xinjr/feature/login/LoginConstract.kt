package com.iuicity.xinjr.feature.login

import com.iuicity.xinjr.base.IBaseView

/**
 * Created by Shui on 2017/7/5.
 */

interface LoginConstract {
    interface View : IBaseView {
        fun gotoMain()
    }

    interface Presenter {
        fun login(account: String, pwd: String)
    }
}

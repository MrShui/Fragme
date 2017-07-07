package com.iuicity.xinjr.base

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.CheckResult
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.iuicity.xinjr.constants.C.REQ_LOGIN
import com.iuicity.xinjr.constants.C.REQ_LOGIN_KEY
import com.iuicity.xinjr.feature.login.LoginActivity
import com.iuicity.xinjr.utils.AccountManager
import com.kaopiz.kprogresshud.KProgressHUD
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * Activity基类，rxjava和其子类生命周期绑定
 * Created by Shui on 2017/7/5.
 */

open class BaseActivity : AppCompatActivity(), LifecycleProvider<ActivityEvent>, IBaseView {
    private var mKProgressHUD: KProgressHUD? = null
    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(ActivityEvent.CREATE)
        initSystemFont()
        AccountManager.get().getSaveInstance(savedInstanceState)
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }

    @CheckResult
    override fun lifecycle(): Observable<ActivityEvent> {
        return lifecycleSubject.hide()
    }

    @CheckResult
    override fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent<T, ActivityEvent>(lifecycleSubject, event)
    }

    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindActivity<T>(lifecycleSubject)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(ActivityEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(ActivityEvent.RESUME)
    }

    @CallSuper
    override fun onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    /**
     * 初始化系统的字体大小，不受设置里的字体大小的影响
     */
    private fun initSystemFont() {
        val res = resources
        val config = Configuration()
        config.setToDefaults()
        res.updateConfiguration(config, res.displayMetrics)
    }

    override fun openLoading() {
        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(this)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setDimAmount(0.5f)
                    .setCancellable(true)
        }
        if (!(mKProgressHUD?.isShowing ?: false)) {
            mKProgressHUD?.show()
        }
    }

    override fun closeLoading() {
        if (mKProgressHUD?.isShowing ?: false) {
            mKProgressHUD?.dismiss()
        }
    }


    override fun showError(errorMsg: String) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun launchLogin(msg: String) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.putExtra(REQ_LOGIN_KEY, REQ_LOGIN)
        startActivityForResult(intent, REQ_LOGIN)
    }

    override fun netError(e: Throwable) {}

    override fun loginBack() {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_LOGIN && resultCode == LoginActivity.RESULT_LOGIN_SUCCESS) {
            loginBack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        AccountManager.get().onSaveInstance(outState)
        super.onSaveInstanceState(outState)
    }
}

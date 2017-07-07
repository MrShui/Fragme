package com.iuicity.xinjr.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CheckResult
import android.support.v4.app.Fragment
import android.view.View
import android.widget.Toast
import com.iuicity.xinjr.App
import com.iuicity.xinjr.constants.C.REQ_LOGIN
import com.iuicity.xinjr.constants.C.REQ_LOGIN_KEY
import com.iuicity.xinjr.feature.login.LoginActivity
import com.kaopiz.kprogresshud.KProgressHUD
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

/**
 * fragment基类，对rxjava可以和其子类的生命周期绑定
 * Created by Shui on 2017/7/5.
 */

open class BaseFragment : Fragment(), LifecycleProvider<FragmentEvent>, IBaseView {
    private var mKProgressHUD: KProgressHUD? = null
    private val lifecycleSubject = BehaviorSubject.create<FragmentEvent>()

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        lifecycleSubject.onNext(FragmentEvent.ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE)
        if (savedInstanceState != null) {
            val isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN)

            val ft = fragmentManager.beginTransaction()
            if (isSupportHidden) {
                ft.hide(this)
            } else {
                ft.show(this)
            }
            ft.commit()
        }
    }


    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(FragmentEvent.RESUME)
    }

    override fun onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    override fun onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY)
        super.onDestroy()
    }

    @CheckResult
    override fun lifecycle(): Observable<FragmentEvent> {
        return lifecycleSubject.hide()
    }

    @CheckResult
    override fun <T> bindUntilEvent(event: FragmentEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent<T, FragmentEvent>(lifecycleSubject, event)
    }


    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindFragment<T>(lifecycleSubject)
    }


    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW)
    }

    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(FragmentEvent.START)
    }

    override fun onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH)
        super.onDetach()
    }

    override fun openLoading() {
        if (activity == null) return

        if (mKProgressHUD == null) {
            mKProgressHUD = KProgressHUD.create(activity)
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
        Toast.makeText(App.sContext, errorMsg, Toast.LENGTH_SHORT).show()
    }

    override fun launchLogin(msg: String) {
        if (activity == null) return

        val intent = Intent(activity, LoginActivity::class.java)
        intent.putExtra(REQ_LOGIN_KEY, REQ_LOGIN)
        startActivityForResult(intent, REQ_LOGIN)
    }

    override fun netError(e: Throwable) {

    }

    override fun loginBack() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_LOGIN && resultCode == LoginActivity.RESULT_LOGIN_SUCCESS) {
            loginBack()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden)
        super.onSaveInstanceState(outState)
    }

    companion object {
        private val STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN"
    }
}

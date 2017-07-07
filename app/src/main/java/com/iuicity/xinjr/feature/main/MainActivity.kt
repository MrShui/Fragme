package com.iuicity.xinjr.feature.main

import android.os.Bundle
import android.os.PersistableBundle
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.util.SparseArray
import android.view.View
import com.iuicity.xinjr.R
import com.iuicity.xinjr.base.BaseActivity
import com.iuicity.xinjr.feature.one.OneFragment
import com.iuicity.xinjr.feature.three.ThreeFragment
import com.iuicity.xinjr.feature.two.TwoFragment
import com.iuicity.xinjr.utils.toast
import kotlinx.android.synthetic.main.activity_main.*


/**
 * 主界面
 * Created by Shui on 2017/7/5.
 */

class MainActivity : BaseActivity(), View.OnClickListener {
    private val LAST_SHOW_ID_KEY = "last_show_id_key"

    private val mFragmentSparseArray = SparseArray<Fragment>()
    @IdRes private var mLastShowId: Int = 0

    init {
        mFragmentSparseArray.append(R.id.tv_one, OneFragment.newInstance())
        mFragmentSparseArray.append(R.id.tv_two, TwoFragment.newInstance())
        mFragmentSparseArray.append(R.id.tv_three, ThreeFragment.newInstance())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            mLastShowId = R.id.tv_one
            loadMultiRootFragment()
        } else {
            mLastShowId = savedInstanceState.getInt(LAST_SHOW_ID_KEY)
        }
        tv_one.setOnClickListener(this)
        tv_two.setOnClickListener(this)
        tv_three.setOnClickListener(this)
    }

    /**
     * 加载多个fragment
     */
    private fun loadMultiRootFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        for (i in 0..mFragmentSparseArray.size() - 1) {
            val key = mFragmentSparseArray.keyAt(i)
            fragmentTransaction.add(R.id.fl_container, mFragmentSparseArray.get(key))
            if (i != 0) {
                fragmentTransaction.hide(mFragmentSparseArray.get(key))
            }
        }
        fragmentTransaction.commit()
    }

    override fun loginBack() {
        toast("登录回来刷新界面")
    }

    override fun onClick(v: View?) {
        if (v == null && v?.id == mLastShowId) return

        showHideFragment(v?.id!!, mLastShowId)
        mLastShowId = v.id
    }

    private fun showHideFragment(@IdRes showId: Int, @IdRes hideId: Int) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.show(mFragmentSparseArray.get(showId))
                .hide(mFragmentSparseArray.get(hideId))
        fragmentTransaction.commit()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putInt(LAST_SHOW_ID_KEY, mLastShowId)
        super.onSaveInstanceState(outState, outPersistentState)
    }
}

package com.iuicity.xinjr.feature.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseActivity;
import com.iuicity.xinjr.feature.home.HomeFragment;
import com.iuicity.xinjr.feature.invest.InvestFragment;
import com.iuicity.xinjr.feature.three.ThreeFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 主界面
 * Created by Shui on 2017/7/5.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.tv_home)
    TextView mTvHome;
    @BindView(R.id.tv_invest)
    TextView mTvInvest;
    @BindView(R.id.tv_mine)
    TextView mTvMine;

    private SparseArray<Fragment> mFragmentSparseArray = new SparseArray<>();
    private SparseArray<TextView> mTextViewSparseArray = new SparseArray<>();
    private @IdRes
    int mLastShowId;
    private final static String LAST_SHOW_ID_KEY = "last_show_id_key";

    {
        mFragmentSparseArray.append(R.id.tv_home, HomeFragment.newInstance());
        mFragmentSparseArray.append(R.id.tv_invest, InvestFragment.newInstance());
        mFragmentSparseArray.append(R.id.tv_mine, ThreeFragment.newInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mTextViewSparseArray.append(R.id.tv_home, mTvHome);
        mTextViewSparseArray.append(R.id.tv_invest, mTvInvest);
        mTextViewSparseArray.append(R.id.tv_mine, mTvMine);

        if (savedInstanceState == null) {
            mLastShowId = R.id.tv_home;
            mTvHome.setActivated(true);
            loadMultiRootFragment();
        } else {
            mLastShowId = savedInstanceState.getInt(LAST_SHOW_ID_KEY);
        }
    }

    /**
     * 加载多个fragment
     */
    private void loadMultiRootFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragmentSparseArray.size(); i++) {
            int key = mFragmentSparseArray.keyAt(i);
            fragmentTransaction.add(R.id.fl_container, mFragmentSparseArray.get(key));
            if (i != 0) {
                fragmentTransaction.hide(mFragmentSparseArray.get(key));
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public void loginBack() {
        Toast.makeText(this, "登录回来刷新界面", Toast.LENGTH_SHORT).show();
    }


    @OnClick({R.id.tv_home, R.id.tv_invest, R.id.tv_mine})
    public void onClick(View v) {
        if (v.getId() == mLastShowId) return;

        mTextViewSparseArray.get(v.getId()).setActivated(true);
        mTextViewSparseArray.get(mLastShowId).setActivated(false);
        showHideFragment(v.getId(), mLastShowId);
        mLastShowId = v.getId();

    }

    private void showHideFragment(@IdRes int showId, @IdRes int hideId) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(mFragmentSparseArray.get(showId))
                .hide(mFragmentSparseArray.get(hideId));
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(LAST_SHOW_ID_KEY, mLastShowId);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

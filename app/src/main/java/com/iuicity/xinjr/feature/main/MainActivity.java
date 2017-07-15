package com.iuicity.xinjr.feature.main;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseActivity;
import com.iuicity.xinjr.feature.home.HomeFragment;
import com.iuicity.xinjr.feature.invest.InvestFragment;
import com.iuicity.xinjr.feature.three.ThreeFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 主界面
 * Created by Shui on 2017/7/5.
 */

public class MainActivity extends BaseActivity {
    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private List<Fragment> mFragments = new ArrayList<>();
    private @IdRes
    int mLastShowId;
    private final static String LAST_SHOW_ID_KEY = "last_show_id_key";

    {
        mFragments.add(HomeFragment.newInstance());
        mFragments.add(InvestFragment.newInstance());
        mFragments.add(ThreeFragment.newInstance());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView(savedInstanceState);
    }

    private void initView(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            initBottomBar();
            loadMultiRootFragment();
        } else {
            mLastShowId = savedInstanceState.getInt(LAST_SHOW_ID_KEY);
        }
    }

    private void initBottomBar() {
        BottomNavigationBar bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_DEFAULT);
        bottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        bottomNavigationBar.setActiveColor(R.color.orange_f5);
        bottomNavigationBar.setInActiveColor(R.color.font_gray_99);

        BottomNavigationItem item1 = new BottomNavigationItem(R.mipmap.home_page, R.string.home);
        BottomNavigationItem item2 = new BottomNavigationItem(R.mipmap.investment, R.string.invest);
        BottomNavigationItem item3 = new BottomNavigationItem(R.mipmap.me, R.string.mine);
        bottomNavigationBar.addItem(item1).addItem(item2).addItem(item3).setFirstSelectedPosition(0).initialise();

        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                showFragment(position);
            }

            @Override
            public void onTabUnselected(int position) {
                hindFragment(position);
            }

            @Override
            public void onTabReselected(int position) {
            }
        });
    }

    /**
     * 加载多个fragment
     */
    private void loadMultiRootFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < mFragments.size(); i++) {
            fragmentTransaction.add(R.id.fl_container, mFragments.get(i));
            if (i != 0) {
                fragmentTransaction.hide(mFragments.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    @Override
    public void loginBack() {
        Toast.makeText(this, "登录回来刷新界面", Toast.LENGTH_SHORT).show();
    }

    private void showFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.show(mFragments.get(position));
        fragmentTransaction.commit();
    }

    private void hindFragment(int position) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.hide(mFragments.get(position));
        fragmentTransaction.commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        outState.putInt(LAST_SHOW_ID_KEY, mLastShowId);
        super.onSaveInstanceState(outState, outPersistentState);
    }
}

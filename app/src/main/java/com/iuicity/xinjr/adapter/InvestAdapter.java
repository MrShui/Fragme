package com.iuicity.xinjr.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.iuicity.xinjr.feature.invest.DueOnDemandFragment;
import com.iuicity.xinjr.feature.invest.RegularIntervalsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 投资adapter
 * Created by Shui on 2017/7/13.
 */

public class InvestAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragments = new ArrayList<>();

    public InvestAdapter(FragmentManager fm) {
        super(fm);
        mFragments.add(RegularIntervalsFragment.newInstance());
        mFragments.add(DueOnDemandFragment.newInstance());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return position == 0 ? "定期" : "活期";
    }
}

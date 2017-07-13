package com.iuicity.xinjr.feature.invest;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.adapter.InvestAdapter;
import com.iuicity.xinjr.base.BaseFragment;
import com.iuicity.xinjr.widget.magicindicator.ColorTransitionPagerTitleView;
import com.iuicity.xinjr.widget.magicindicator.CommonNavigator;
import com.iuicity.xinjr.widget.magicindicator.CommonNavigatorAdapter;
import com.iuicity.xinjr.widget.magicindicator.IPagerIndicator;
import com.iuicity.xinjr.widget.magicindicator.IPagerTitleView;
import com.iuicity.xinjr.widget.magicindicator.LinePagerIndicator;
import com.iuicity.xinjr.widget.magicindicator.MagicIndicator;
import com.iuicity.xinjr.widget.magicindicator.SimplePagerTitleView;
import com.iuicity.xinjr.widget.magicindicator.ViewPagerHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 投资界面
 * Created by Shui on 2017/7/6.
 */

public class InvestFragment extends BaseFragment {
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    private Unbinder unbinder;

    public static InvestFragment newInstance() {

        Bundle args = new Bundle();

        InvestFragment fragment = new InvestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_invist, container, false);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        InvestAdapter adapter = new InvestAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        initIndicator(adapter);
    }

    private void initIndicator(final InvestAdapter adapter) {
        CommonNavigator commonNavigator = new CommonNavigator(mActivity);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return adapter.getCount();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(adapter.getPageTitle(index));
                simplePagerTitleView.setTextSize(16);
                simplePagerTitleView.setNormalColor(Color.WHITE);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index, false);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.WHITE);
                return indicator;
            }
        });
        mMagicIndicator.setNavigator(commonNavigator);
//        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
//        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//        titleContainer.setDividerPadding(UIUtil.dip2px(mActivity, 25));
        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

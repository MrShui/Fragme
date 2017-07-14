package com.iuicity.xinjr.feature.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.adapter.BannerAdapter;
import com.iuicity.xinjr.adapter.ProductAdapter;
import com.iuicity.xinjr.base.BaseFragment;
import com.iuicity.xinjr.network.helper.RxSchedulersHelper;
import com.iuicity.xinjr.utils.StatusBarUtil;
import com.iuicity.xinjr.widget.magicindicator.MagicIndicator;
import com.iuicity.xinjr.widget.magicindicator.SolidCircleNavigator;
import com.iuicity.xinjr.widget.magicindicator.ViewPagerHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 首页
 * Created by Shui on 2017/7/6.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.vp_banner)
    ViewPager mVpBanner;
    @BindView(R.id.vp_product)
    ViewPager mVpProduct;
    @BindView(R.id.cl_container)
    ConstraintLayout mClContainer;
    @BindView(R.id.magic_indicator)
    MagicIndicator mMagicIndicator;

    private Unbinder unbinder;
    private List<String> mBannerUrls = new ArrayList<>();
    private Disposable mBannerSubscribe;
    private List<String> mProductData;

    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    {
        mBannerUrls.add("http://img0.imgtn.bdimg.com/it/u=2521048355,2266300277&fm=26&gp=0.jpg");
        mBannerUrls.add("http://img4.imgtn.bdimg.com/it/u=2462771328,1930820114&fm=26&gp=0.jpg");
        mBannerUrls.add("http://img1.imgtn.bdimg.com/it/u=1336771871,3870056346&fm=26&gp=0.jpg");
        mBannerUrls.add("http://img3.imgtn.bdimg.com/it/u=2286534428,2694273569&fm=26&gp=0.jpg");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_home, container, false);
        StatusBarUtil.stateBarSetting(mActivity, true);
        unbinder = ButterKnife.bind(this, inflate);
        initView();
        return inflate;
    }

    private void initView() {
        initBanner();
        initProduct();
    }

    private void initProduct() {
        mVpProduct.setOffscreenPageLimit(3);
        mProductData = Arrays.asList("hehe", "haha", "xixi", "adss");
        ProductAdapter productAdapter = new ProductAdapter(mProductData, mActivity);
        mVpProduct.setAdapter(productAdapter);
        mVpProduct.setPageMargin(20);
        mVpProduct.setOffscreenPageLimit(3);
        mVpProduct.setCurrentItem(1);
        mClContainer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return mVpProduct.dispatchTouchEvent(event);
            }
        });
        initIndicator();
    }

    private void initIndicator() {
        SolidCircleNavigator circleNavigator = new SolidCircleNavigator(mActivity);
        circleNavigator.setCircleCount(mProductData.size());
        circleNavigator.setSelectColor(ContextCompat.getColor(mActivity, R.color.orange_ef));
        circleNavigator.setNormalColor(ContextCompat.getColor(mActivity, R.color.gray_d8));
        circleNavigator.setCircleClickListener(new SolidCircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                mVpProduct.setCurrentItem(index);
            }
        });
        mMagicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(mMagicIndicator, mVpProduct);
        mMagicIndicator.onPageSelected(1);
    }

    private void initBanner() {
        BannerAdapter adapter = new BannerAdapter(mBannerUrls, mActivity);
        mVpBanner.setAdapter(adapter);
        mVpBanner.setCurrentItem(Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % mBannerUrls.size());
        //点击取消轮播，抬起手指继续轮播
        mVpBanner.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        cancelCarousel();
                        break;
                    case MotionEvent.ACTION_UP:
                        startCarousel();
                        break;
                }
                return false;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        startCarousel();
    }

    /**
     * 取消banner轮播
     */
    private void cancelCarousel() {
        if (mBannerSubscribe != null && !mBannerSubscribe.isDisposed()) {
            mBannerSubscribe.dispose();
        }
    }

    /**
     * 启动banner轮播
     */
    private void startCarousel() {
        cancelCarousel();
        mBannerSubscribe = Observable.interval(3, 3, TimeUnit.SECONDS)
                .compose(RxSchedulersHelper.<Long>ioMain())
                .compose(this.<Long>bindToLifecycle())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        int currentItem = mVpBanner.getCurrentItem();
                        mVpBanner.setCurrentItem(++currentItem);
                    }
                });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        //切换到别的界面停止轮播，切换回来开启轮播
        if (hidden) {
            cancelCarousel();
        } else {
            startCarousel();
        }
    }
}

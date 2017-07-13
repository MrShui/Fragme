package com.iuicity.xinjr.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.iuicity.xinjr.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页轮播图adapter
 * Created by Shui on 2017/7/13.
 */

public class BannerAdapter extends PagerAdapter {
    List<ImageView> mImageViews = new ArrayList<>();

    public BannerAdapter(List<String> banners, Context context) {
        for (String banner : banners) {
            ImageView imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Utils.loadImage(banner, imageView, 0, 0);
            mImageViews.add(imageView);
        }
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        position %= mImageViews.size();
        ImageView imageView = mImageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        position %= mImageViews.size();
        container.removeView(mImageViews.get(position));
    }
}

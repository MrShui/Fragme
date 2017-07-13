package com.iuicity.xinjr.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.iuicity.xinjr.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shui on 2017/7/13.
 */

public class ProductAdapter extends PagerAdapter {
    private List<View> mViews = new ArrayList<>();

    public ProductAdapter(List<String> datas, Context context) {
        for (String data : datas) {
            View view = View.inflate(context, R.layout.item_product, null);
            mViews.add(view);
        }
    }

    @Override
    public int getCount() {
        return mViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = mViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViews.get(position));
    }
}

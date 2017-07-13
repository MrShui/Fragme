package com.iuicity.xinjr.widget.magicindicator;

import android.content.Context;

/**
 * 带颜色渐变和缩放的指示器标题
 * 博客: http://hackware.lucode.net
 * Created by hackware on 2016/6/26.
 */
public class ScaleTransitionPagerTitleView extends ColorTransitionPagerTitleView {
    private final Context mContext;
    private float mMinScale = 0.8f;

    public ScaleTransitionPagerTitleView(Context context) {
        super(context);
        mContext = context;
    }

    public ScaleTransitionPagerTitleView(Context context, Float minScales) {
        super(context);
        mContext = context;
        mMinScale = minScales;
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);    // 实现颜色渐变
        setScaleX(mMinScale + (1.0f - mMinScale) * enterPercent);
        setScaleY(mMinScale + (1.0f - mMinScale) * enterPercent);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);    // 实现颜色渐变
        setScaleX(1.0f + (mMinScale - 1.0f) * leavePercent);
        setScaleY(1.0f + (mMinScale - 1.0f) * leavePercent);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        super.onSelected(index, totalCount);
        // TODO: 2017/7/13 上个项目的
//        getPaint().setShader(new LinearGradient(0, 0, 0, getLineHeight()
//                , ContextCompat.getColor(mContext, R.color.home_title_start_color)
//                , ContextCompat.getColor(mContext, R.color.home_title_end_color), Shader.TileMode.REPEAT));
//        getPaint().setFakeBoldText(true);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        super.onDeselected(index, totalCount);
        getPaint().setShader(null);
        getPaint().setFakeBoldText(false);
    }

    public float getMinScale() {
        return mMinScale;
    }

    public void setMinScale(float minScale) {
        mMinScale = minScale;
    }
}

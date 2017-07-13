package com.iuicity.xinjr.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.iuicity.xinjr.App;

/**
 * 公共工具类
 * Created by Shui on 2017/7/12.
 */

public class Utils {
    /**
     * 使用glide加载图片
     *
     * @param imgUrl      图片地址
     * @param imageView   加载图片控件
     * @param placeholder 未加载图片占位图
     * @param error       加载错误占位图
     */
    public static void loadImage(String imgUrl, ImageView imageView, int placeholder, int error) {
        Glide.with(App.sContext)
                .load(imgUrl)
                .placeholder(placeholder)
                .error(error)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(imageView);
    }
}

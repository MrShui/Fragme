package com.iuicity.xinjr.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.iuicity.xinjr.R;

import java.util.List;

/**
 * Created by Shui on 2017/7/5.
 */

public class TestAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public TestAdapter(@Nullable List<String> data) {
        super(R.layout.item_test, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_test, item);
    }
}

package com.iuicity.xinjr.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.iuicity.xinjr.R

/**
 * Created by Shui on 2017/7/5.
 */

class TestAdapter(data: List<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_test, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tv_test, item)
    }
}

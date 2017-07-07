package com.iuicity.xinjr.feature.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iuicity.xinjr.R
import com.iuicity.xinjr.base.BaseFragment

/**
 * Created by Shui on 2017/7/6.
 */

class OneFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater!!.inflate(R.layout.fragment_one, container, false)
        return inflate
    }

    companion object {
        fun newInstance(): OneFragment {

            val args = Bundle()

            val fragment = OneFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

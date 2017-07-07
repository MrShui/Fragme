package com.iuicity.xinjr.feature.two

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iuicity.xinjr.R
import com.iuicity.xinjr.base.BaseFragment

/**
 * Created by Shui on 2017/7/6.
 */

class TwoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater!!.inflate(R.layout.fragment_two, container, false)
        return inflate
    }

    companion object {
        fun newInstance(): TwoFragment {

            val args = Bundle()

            val fragment = TwoFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

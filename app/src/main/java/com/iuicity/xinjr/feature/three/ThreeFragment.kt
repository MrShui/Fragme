package com.iuicity.xinjr.feature.three

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.iuicity.xinjr.R
import com.iuicity.xinjr.base.BaseFragment

/**
 * Created by Shui on 2017/7/6.
 */

class ThreeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflate = inflater!!.inflate(R.layout.fragment_three, container, false)
        return inflate
    }

    companion object {
        fun newInstance(): ThreeFragment {

            val args = Bundle()

            val fragment = ThreeFragment()
            fragment.arguments = args
            return fragment
        }
    }
}

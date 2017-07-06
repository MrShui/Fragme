package com.iuicity.xinjr.feature.three;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseFragment;

/**
 * Created by Shui on 2017/7/6.
 */

public class ThreeFragment extends BaseFragment {
    public static ThreeFragment newInstance() {

        Bundle args = new Bundle();

        ThreeFragment fragment = new ThreeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_three, container, false);
        return inflate;
    }
}

package com.iuicity.xinjr.feature.invest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iuicity.xinjr.R;
import com.iuicity.xinjr.base.BaseFragment;

/**
 * 活期界面
 * Created by Shui on 2017/7/13.
 */

public class DueOnDemandFragment extends BaseFragment {
    public static DueOnDemandFragment newInstance() {

        Bundle args = new Bundle();

        DueOnDemandFragment fragment = new DueOnDemandFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_due_on_demand, container, false);
        return inflate;
    }
}

package com.shuiyouwen.fragme.feature.module1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.shuiyouwen.fragme.R;
import com.shuiyouwen.fragme.base.BaseActivity;

/**
 * Created by Shui on 16/12/3.
 */

public class NetErrorActivity extends BaseActivity {
    public final static String METHOD_NAME = "method_name";

    public final static int NET_ERROR_RES = 101;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net_error);

        final String methodName = getIntent().getStringExtra(METHOD_NAME);

        Button btnRetry = (Button) findViewById(R.id.btn_retry);
        btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra(METHOD_NAME, methodName);
                setResult(NET_ERROR_RES, intent);
                finish();
            }
        });
    }
}

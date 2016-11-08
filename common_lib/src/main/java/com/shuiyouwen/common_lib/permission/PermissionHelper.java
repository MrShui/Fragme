package com.shuiyouwen.common_lib.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.hwangjr.rxbus.RxBus;
import com.hwangjr.rxbus.annotation.Subscribe;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public class PermissionHelper {
    private final static int REQ_PERMISSION = 102;
    private static PermissionHelper mInstance;
    private IPermissionListener mPermissionListener;
    private Context mContext;

    private PermissionHelper() {
    }

    public static PermissionHelper getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionHelper();
        }
        return mInstance;
    }

    public void request(Context context, String[] permissions, IPermissionListener permissionListener) {
        mContext = context;
        mPermissionListener =  permissionListener;
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //低于6.0无需动态申请权限
            mPermissionListener.onAllowed();
            return;
        }

        Intent intent = new Intent();
        intent.setClass(context, PermissionActivity.class);
        intent.putExtra(PermissionActivity.PERMISSIONS_PARAMS, permissions);
        context.startActivity(intent);
    }

    public void getPermissionFromSettings(Activity activity) {
        Uri packageURI = Uri.parse("package:" + mContext.getPackageName());
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
        activity.startActivityForResult(intent, REQ_PERMISSION);
    }

    IPermissionListener getPermissionListener() {
        return mPermissionListener;
    }
}

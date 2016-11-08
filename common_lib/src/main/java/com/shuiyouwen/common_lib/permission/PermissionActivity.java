package com.shuiyouwen.common_lib.permission;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;

import com.hwangjr.rxbus.RxBus;

import java.util.ArrayList;
import java.util.List;

/**
 * 6.0权限申请activity
 * Created by Administrator on 2016/11/8 0008.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class PermissionActivity extends FragmentActivity {
    public final static String PERMISSIONS_PARAMS = "permissions_params";
    public final static int REQUEST_PERMISSION = 103;
    private List<String> mShouldRequestPermission;
    private IPermissionListener mPermissionListener = PermissionHelper.getInstance().getPermissionListener();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] requestPermissions = getIntent().getStringArrayExtra(PERMISSIONS_PARAMS);
        permissionHandler(requestPermissions);
    }

    private void permissionHandler(String[] requestPermissions) {
        mShouldRequestPermission = checkPermissions(requestPermissions);
        if (mShouldRequestPermission == null || mShouldRequestPermission.size() <= 0) {
            //所需申请的权限已拥有
            mPermissionListener.onAllowed();
            finish();
            return;
        }

        if (isNeadExplain(mShouldRequestPermission)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("申请权限");
            builder.setMessage("亲！给个权限呗！");
            builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    requestPermissions(mShouldRequestPermission.toArray(new String[mShouldRequestPermission.size()]), REQUEST_PERMISSION);
                }
            });
            builder.show();
        } else {
            requestPermissions(mShouldRequestPermission.toArray(new String[mShouldRequestPermission.size()]), REQUEST_PERMISSION);
        }
    }

    private List<String> checkPermissions(String[] requestPermissions) {
        List<String> shouldRequestPermission = new ArrayList<>();
        for (String permission : requestPermissions) {
            if (!(checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)) {
                shouldRequestPermission.add(permission);
            }
        }
        return shouldRequestPermission;
    }

    /**
     * 是否需要向用户为何申请权限
     *
     * @return
     */
    private boolean isNeadExplain(List<String> shouldRequestPermission) {
        for (String permission : shouldRequestPermission) {
            if (shouldShowRequestPermissionRationale(permission)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_PERMISSION && grantResults.length == mShouldRequestPermission.size()) {
            List<String> requestFailPermissions = getRequestFailPermissions(grantResults, permissions);
            if (requestFailPermissions == null || requestFailPermissions.size() <= 0) {
                //全部权限申请成功
                mPermissionListener.onAllowed();
                finish();
            } else {
                //权限申请失败
                mPermissionListener.onRefused(requestFailPermissions);
                finish();
            }
        }
    }

    private List<String> getRequestFailPermissions(int[] grantResults, String[] permissions) {
        List<String> requestFailPermissions = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            if (!(grantResults[i] == PackageManager.PERMISSION_GRANTED)) {
                requestFailPermissions.add(permissions[i]);
            }
        }
        return requestFailPermissions;
    }
}
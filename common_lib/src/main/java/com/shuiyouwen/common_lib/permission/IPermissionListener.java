package com.shuiyouwen.common_lib.permission;

import java.util.List;

/**
 * Created by Administrator on 2016/11/8 0008.
 */

public interface IPermissionListener {
    /**
     * 申请成功
     */
    void onAllowed();

    /**
     * 申请失败
     *
     * @param permissions 申请失败的权限
     */
    void onRefused(List<String> permissions);
}

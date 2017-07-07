package com.iuicity.xinjr.utils

import android.app.Activity
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextUtils
import android.widget.Toast

/**
 * 扩展方法
 * Created by Shui on 2017/7/6.
 */
fun Activity.toast(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

fun Activity.isEmpty(str: String): Boolean {
    return TextUtils.isEmpty(str)
}

fun Activity.isEmpty(str: Editable): Boolean {
    return TextUtils.isEmpty(str)
}

fun Fragment.toast(msg: String) {
    if (activity != null) {
        Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
    }
    return
}

fun Fragment.isEmpty(str: String): Boolean {
    return TextUtils.isEmpty(str)
}

fun Fragment.isEmpty(str: Editable): Boolean {
    return TextUtils.isEmpty(str)
}

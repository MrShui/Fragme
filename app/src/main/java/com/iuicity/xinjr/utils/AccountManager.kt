package com.iuicity.xinjr.utils

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable

import com.iuicity.xinjr.network.bean.resp.LoginResp

/**
 * 账户管理相关
 * Created by Shui on 2017/7/5.
 */

class AccountManager private constructor() {
    private val SAVE_INSTANCE: String = "save_instance"

    var userInfo: LoginResp? = null
    private var mTestFlag: Boolean = false
    private var mTestString: String? = null

    /**
     * activity快要被杀死时，保存数据
     * @param bundle
     */
    fun onSaveInstance(bundle: Bundle) {
        val data = Data(userInfo!!, mTestFlag, mTestString!!)
        bundle.putParcelable(SAVE_INSTANCE, data)
    }

    /**
     * 重新进入activity取出保存的数据
     */
    fun getSaveInstance(bundle: Bundle?) {
        if (bundle == null) return

        val data = bundle.getParcelable<Data>(SAVE_INSTANCE)
        userInfo = data.loginResp
        mTestFlag = data.isTestFlag
        mTestString = data.testString
    }

    private class Data(val loginResp: LoginResp, val isTestFlag: Boolean, val testString: String) : Parcelable {
        constructor(parcel: Parcel) : this(
                parcel.readParcelable(LoginResp::class.java.classLoader),
                parcel.readByte() != 0.toByte(),
                parcel.readString())

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeParcelable(loginResp, flags)
            parcel.writeByte(if (isTestFlag) 1 else 0)
            parcel.writeString(testString)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Data> {
            override fun createFromParcel(parcel: Parcel): Data {
                return Data(parcel)
            }

            override fun newArray(size: Int): Array<Data?> {
                return arrayOfNulls(size)
            }
        }
    }


    companion object {
        fun get(): AccountManager {
            return Inner.accountManager
        }
    }

    private object Inner {
        val accountManager = AccountManager()
    }
}

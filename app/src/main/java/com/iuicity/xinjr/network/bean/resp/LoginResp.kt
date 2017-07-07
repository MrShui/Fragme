package com.iuicity.xinjr.network.bean.resp

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Shui on 2017/7/7.
 */
data class LoginResp(val token: String, val uid: String, val type: String, val status: String) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(token)
        parcel.writeString(uid)
        parcel.writeString(type)
        parcel.writeString(status)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<LoginResp> {
        override fun createFromParcel(parcel: Parcel): LoginResp {
            return LoginResp(parcel)
        }

        override fun newArray(size: Int): Array<LoginResp?> {
            return arrayOfNulls(size)
        }
    }
}
package com.iuicity.xinjr.network.exception

/**
 * token失效异常
 * Created by Administrator on 2016/11/8 0008.
 */

class TokenException(var errorMsg: String?, var errorCode: Int) : Exception(errorMsg)

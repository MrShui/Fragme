package com.iuicity.xinjr.network.exception

/**
 * 服务器异常
 * Created by Administrator on 2016/11/8 0008.
 */
class ServerException(val msg: String, val code: Int) : Exception(msg)

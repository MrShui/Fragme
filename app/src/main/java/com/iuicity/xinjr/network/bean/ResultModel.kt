package com.iuicity.xinjr.network.bean

import java.util.*

/**
 * @author: Mr.Shui
 * *
 * @time: 16/11/6 18:27
 * *
 * @desc: 通用后台返回结果的包装实体类
 */

class ResultModel<T> {
    var code: Int = 0
    var msg: String? = null
    var data: T? = null

    companion object {
        val REQ_SUCCESS = 0
        //1021异设备登录 1018token失效 1019token不能为空
        val REQ_TOKEN_ERROR = Arrays.asList(1021, 1018, 1019, 1013)
        //1005    生成密钥参数错误  1006    生成密钥失败  1007    解密参数错误  1008    获取私钥失败  1009    解密失败
        val REQ_DECODE_ERROR = Arrays.asList(1005, 1006, 1007, 1008, 1009)
    }
}

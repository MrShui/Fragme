package com.iuicity.xinjr.network.bean;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Mr.Shui
 * @time: 16/11/6 18:27
 * @desc: 通用后台返回结果的包装实体类
 */

public class ResultModel<T> {
    public static final int REQ_SUCCESS = 0;
    //1021异设备登录 1018token失效 1019token不能为空
    public static final List<Integer> REQ_TOKEN_ERROR = Arrays.asList(1021, 1018, 1019, 1013);
    //1005    生成密钥参数错误  1006    生成密钥失败  1007    解密参数错误  1008    获取私钥失败  1009    解密失败
    public static final List<Integer> REQ_DECODE_ERROR = Arrays.asList(1005, 1006, 1007, 1008, 1009);

    private int code;
    private String msg;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

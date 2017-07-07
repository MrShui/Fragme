package com.iuicity.xinjr.utils

import android.util.Log

/**
 * Log统一管理类

 * 打印时添加调用日志处的类名，函数名，行数信息


 */
object L {

    var isDebug = true

    /**
     * Log单次只能输出不多于4000字符，多于的要分次循环输出
     */
    private val MAX_LOG_LENGTH = 3000

    private val TAG = "bestpay"

    // 下面四个是默认tag的函数
    fun i(msg: String?) {
        if (isDebug && msg != null)
            Log.i(TAG, stackTraceMsg + msg)
    }

    fun d(tag: String, msg: String?) {
        if (isDebug && msg != null) {

            for (i in 0..msg.length / MAX_LOG_LENGTH) {

                val startIndex = i * MAX_LOG_LENGTH
                val endIndex = if (msg.length <= (i + 1) * MAX_LOG_LENGTH)
                    msg.length
                else
                    (i + 1) * MAX_LOG_LENGTH

                Log.d(tag, stackTraceMsg + msg.substring(startIndex, endIndex))
            }
        }
    }

    fun print(t: Throwable) {
        if (isDebug) {
            t.printStackTrace()
        }
    }

    fun d(msg: String) {
        d(TAG, msg)
    }

    fun e(msg: String?) {
        if (isDebug && msg != null)
            Log.e(TAG, stackTraceMsg + msg)
    }

    fun v(msg: String?) {
        if (isDebug && msg != null)
            Log.v(TAG, stackTraceMsg + msg)
    }

    // 下面是传入自定义tag的函数
    fun i(tag: String, msg: String?) {
        if (isDebug && msg != null)
            Log.i(tag, stackTraceMsg + msg)
    }

    fun e(tag: String, msg: String?) {
        if (isDebug && msg != null)
            Log.i(tag, stackTraceMsg + msg)
    }

    fun e(tag: String, msg: String?, t: Throwable) {
        if (isDebug && msg != null)
            Log.i(tag, msg, t)
    }

    fun v(tag: String, msg: String?) {
        if (isDebug && msg != null)
            Log.i(tag, stackTraceMsg + msg)
    }

    fun w(tag: String, msg: String?) {
        if (isDebug && msg != null)
            Log.w(tag, stackTraceMsg + msg)
    }

    fun w(tag: String, msg: String?, t: Throwable) {
        if (isDebug && msg != null)
            Log.w(tag, msg, t)
    }

    /**
     * 打印时添加调用日志处的类名，函数名，行数信息
     */
    private val stackTraceMsg: String
        get() {
            val stackTrace = Thread.currentThread().stackTrace[4]
            return stackTrace.fileName + "(" + stackTrace.lineNumber + ") " + stackTrace.methodName + ": "
        }
}

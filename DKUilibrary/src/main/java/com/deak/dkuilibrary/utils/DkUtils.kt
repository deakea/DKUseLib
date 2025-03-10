package com.deak.dkuilibrary.utils

import android.content.Context
import android.content.res.Resources

/**
 *@time 创建时间:2025/3/10
 *@user lxh
 *@version
 *@desc
 **/
class DkUtils private constructor() {
    companion object {
        @Volatile
        private var instance: DkUtils? = null
        private var appContext: Context? = null

        fun getInstance(context: Context): DkUtils {
            return instance ?: synchronized(this) {
                instance ?: DkUtils().also {
                    instance = it
                    appContext = context.applicationContext
                }
            }
        }
    }


    fun getAppContext(): Context {
        return if (appContext == null) {
            throw NullPointerException("you should call init DKAppUtils first")
        } else appContext!!
    }
}
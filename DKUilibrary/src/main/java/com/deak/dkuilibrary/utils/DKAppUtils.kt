package com.deak.dkuilibrary.utils

import android.content.Context
import android.content.res.Resources

/**
 *@time 创建时间:2025/3/10
 *@user lxh
 *@version
 *@desc
 **/
object DKAppUtils {
    private var dkUtils: DkUtils? = null

    fun init(context: Context) {
        dkUtils = DkUtils.getInstance(context)
    }
    fun getResource(): Resources {
        return dkUtils?.getAppContext()?.resources?:Resources.getSystem()
    }
}
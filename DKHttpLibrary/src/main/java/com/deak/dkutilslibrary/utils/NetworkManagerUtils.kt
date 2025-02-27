package com.deak.dkutilslibrary.utils

import android.Manifest.permission
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.annotation.RequiresPermission

/**
 *@time 创建时间:2025/2/27
 *@user lxh
 *@version
 *@desc 网络管理类工具
 **/
object NetworkManagerUtils {
    private var isConnected: Boolean = false
    @RequiresPermission(permission.ACCESS_NETWORK_STATE)
    fun setOnNetworkStatusChange(context:Context){
        val info: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        info.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                isConnected = true
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                isConnected = false
            }
        })
    }
    @RequiresPermission(permission.ACCESS_NETWORK_STATE)
    fun isConnected(): Boolean {
        return isConnected
    }
}
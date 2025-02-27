package com.deak.uselibexample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.deak.dkutilslibrary.utils.NetworkManagerUtils
import com.deak.uselibexample.databinding.ActivityConnectUtilsBinding

/**
 *@time 创建时间:2025/2/27
 *@user lxh
 *@version
 *@desc 工具类使用Demo
 **/
class ConnectedUtilsActivity :FragmentActivity(){
    private lateinit var binding: ActivityConnectUtilsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        NetworkManagerUtils.setOnNetworkStatusChange(this)
        initView()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() {
        binding.apply {
            tvDKConnectedStatus.setOnClickListener {
                tvDKConnectedStatus.text = "检测网络连接状态(${NetworkManagerUtils.isConnected()})"
            }
        }
    }
}
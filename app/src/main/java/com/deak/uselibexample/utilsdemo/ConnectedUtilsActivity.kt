package com.deak.uselibexample.utilsdemo

import android.R
import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
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
        val winContent = findViewById<View>(R.id.content) as ViewGroup
        if (winContent.childCount > 0) {
            val rootView = winContent.getChildAt(0) as ViewGroup
            if (rootView != null) {
                rootView.fitsSystemWindows = true
            }
        }
        binding = ActivityConnectUtilsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge(SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT),
            SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))
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
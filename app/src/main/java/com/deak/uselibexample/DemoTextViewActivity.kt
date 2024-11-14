package com.deak.uselibexample

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.deak.uselibexample.databinding.ActivityRoundTextviewBinding

/**
 * @time 创建时间:2024/11/14
 * @user lxh
 * @desc
 */
class DemoTextViewActivity : FragmentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRoundTextviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}

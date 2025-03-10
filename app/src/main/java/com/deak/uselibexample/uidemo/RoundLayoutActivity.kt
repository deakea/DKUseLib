package com.deak.uselibexample.uidemo

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.core.view.updatePadding
import com.deak.dkuilibrary.utils.DimensionUtils.dp
import com.deak.dkuilibrary.utils.DimensionUtils.px
import com.deak.dkuilibrary.view.DKTextView
import com.deak.uselibexample.R
import com.deak.uselibexample.databinding.ActivityRoundLayoutBinding
import kotlin.random.Random

/**
 *@time 创建时间:2024/6/25
 *@user
 *@version
 *@desc 带有圆角的各布局使用示例
 **/
class RoundLayoutActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRoundLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        for (i in 0..10) {
            val num = Random.nextInt(0,30000)
            val textView = DKTextView(this).apply {
                layoutParams = ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(5,5,5,5)
                }
                setPadding(5.dp().toInt())
                setSingleColor(Color.RED)
                setRadius(10f)
                setTextColor(Color.WHITE)
                text = "这是标签${num}"
                textSize = 18f
                postInvalidate()

            }

            binding.tagLayout.addView(textView)
        }
    }
}
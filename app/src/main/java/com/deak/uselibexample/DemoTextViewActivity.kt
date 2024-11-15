package com.deak.uselibexample

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.deak.uselibexample.databinding.ActivityRoundTextviewBinding

/**
 * @time 创建时间:2024/11/14
 * @user lxh
 * @desc
 */
class DemoTextViewActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityRoundTextviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            with(text3) {
                setOnClickListener {
                    text = "彩虹变身！彩色变色龙。"
                    setTextGradientColor(
                        colorArray = intArrayOf(
                            Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA
                        ),
                        floatArrayOf(
                            0f, 0.25f, 0.5f, 0.75f, 1f
                        ),
                        angle = 60f
                    )
                }
            }
            with(text4) {
                var isChangeStrokeColor = false
                setOnClickListener {
                    if (!isChangeStrokeColor) {
                        isChangeStrokeColor = true
                        text = "彩色衣服已换，点击卸甲"
                        setTextStrokeGradient(
                            intArrayOf(
                                Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA
                            ),
                            floatArrayOf(
                                0f, 0.25f, 0.5f, 0.75f, 1f
                            )
                        )
                    } else {
                        isChangeStrokeColor = false
                        text = "点击为我换上彩色外衣"
                        setTextStroke(color = Color.BLACK)
                    }
                }
            }
            with(text5){
                setTextGradientColor(
                    colorArray = intArrayOf(
                        Color.RED, Color.CYAN, Color.GREEN, Color.YELLOW, Color.MAGENTA
                    ),
                    floatArrayOf(
                        0f, 0.25f, 0.5f, 0.75f, 1f
                    ),
                    angle = 60f
                )
            }
        }

    }

}

package com.deak.dkuilibrary.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import com.deak.dkuilibrary.dk_interface.LayoutParse
import com.deak.dkuilibrary.impl.CommonLayoutParse

/**
 *@time 创建时间:2024/6/25
 *@user
 *@version
 *@desc 自定义圆角和渐变背景的线性控件\描边stroke
 **/
class RoundFrameLayout(context: Context, attrs: AttributeSet?) :
    FrameLayout(context, attrs), LayoutParse by CommonLayoutParse(context, attrs) {


    init {
        initParse(this)
        setBackgroundColor(Color.TRANSPARENT)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setLayoutSet()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCanvas(canvas)
    }


}
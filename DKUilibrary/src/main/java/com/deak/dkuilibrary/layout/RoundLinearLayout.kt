package com.deak.dkuilibrary.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.LinearLayoutCompat
import com.deak.dkuilibrary.dk_interface.LayoutParse
import com.deak.dkuilibrary.impl.CommonLayoutParse

/**
 *@time 创建时间:2024/6/25
 *@user
 *@version
 *@desc 自定义圆角和渐变背景的线性控件\描边stroke
 **/
class RoundLinearLayout(context: Context, attrs: AttributeSet?) :
    LinearLayoutCompat(context, attrs), LayoutParse by CommonLayoutParse(context, attrs) {


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
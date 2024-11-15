package com.deak.dkuilibrary.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.deak.dkuilibrary.dk_interface.LayoutParse
import com.deak.dkuilibrary.dk_interface.TextViewInterface
import com.deak.dkuilibrary.impl.CommonLayoutParse
import com.deak.dkuilibrary.impl.TextViewImpl
import kotlin.math.min

/**
 *@time 创建时间:2024/6/25
 *@user
 *@version
 *@desc 自定义圆角和渐变背景的TextView控件\描边stroke,可设置圆角本背景，圆角字体，渐变字体等
 **/
class DKTextView(context: Context, attrs: AttributeSet?) :
    AppCompatTextView(context, attrs), LayoutParse by CommonLayoutParse(context, attrs),
    TextViewInterface by TextViewImpl(context, attrs) {


    init {
        initParse(this)
        initTextView(this)
        setBackgroundColor(Color.TRANSPARENT)
        includeFontPadding = false
    }

//    override fun layout(l: Int, t: Int, r: Int, b: Int) {
//        super.layout(l- min(0,getShadowX()) , t-min(0,getShadowY()), r+min(0,getShadowX()), b+ min(0,getShadowY()))
//    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setLayoutSet()
        setTextBounds(text.toString())
    }

    override fun onDraw(canvas: Canvas) {
        drawTextViewCanvas(canvas)
//        setShadowLayer(8f,0f,40f,Color.BLACK)
        super.onDraw(canvas)
        drawCanvas(canvas)
        drawTextViewCanvasAfter(canvas)
    }


}
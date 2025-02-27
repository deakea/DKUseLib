package com.deak.dkuilibrary.layout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import com.deak.dkuilibrary.dk_interface.LayoutParse
import com.deak.dkuilibrary.dk_interface.TextViewInterface
import com.deak.dkuilibrary.impl.CommonLayoutParse
import com.deak.dkuilibrary.impl.TextViewImpl

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
        addTextChangedListener {
            setBorderTextView(it)
        }
        this.includeFontPadding = false
    }

    override fun setIncludeFontPadding(includepad: Boolean) {
        super.setIncludeFontPadding(false)
    }

    override fun setLayoutParams(params: ViewGroup.LayoutParams) {
        setDKLayoutParams(params)
        super.setLayoutParams(params)
    }

    override fun setTextDirection(textDirection: Int) {
        setDKTextDirection(textDirection)
        super.setTextDirection(textDirection)
    }

    override fun setLayoutDirection(layoutDirection: Int) {
       setDKLayoutDirection(layoutDirection)
        super.setLayoutDirection(layoutDirection)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        onDKMeasure(widthMeasureSpec, heightMeasureSpec,{
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        })
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        onDKLayout(changed, left, top, right, bottom)
    }

    override fun setDKMeasuredDimension(newWidth: Int, newHeight: Int) {
        setMeasuredDimension(newWidth, newHeight)
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        setLayoutSet()
        setTextBounds(text.toString())
    }


    override fun onDraw(canvas: Canvas) {
        drawTextViewCanvas(canvas)
        setTextSuperCanvas(canvas)
        super.onDraw(canvas);

        drawCanvas(canvas)
        drawTextViewCanvasAfter(canvas)
    }


}
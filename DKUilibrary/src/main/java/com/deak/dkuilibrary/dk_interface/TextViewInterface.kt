package com.deak.dkuilibrary.dk_interface

import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import android.widget.TextView

/**
 *@time 创建时间:2024/11/14
 *@user lxh
 *@version
 *@desc
 **/
interface TextViewInterface {
    /**
     * 初始化TextView
     *
     * @param view
     */
    fun initTextView(view: TextView)
    /**
     * 获取文字主体的区域
     *@param content 文字内容
     */
    fun setTextBounds(content:String)

    /**
     * 设置字体的阴影属性
     *
     * @param radius 阴影圆角
     * @param shadowX 阴影X轴方向偏移量
     * @param shadowY 阴影Y轴方向偏移量
     */
    fun setTextShadow(radius:Float=0f,shadowX:Float = 0f,shadowY:Float = 0f)
    fun getShadowX():Int
    fun getShadowY():Int
    fun setTextGradientColor(colorArray: IntArray, positionArray: FloatArray,angle:Float=0f)
    fun enableTextGradient(enable:Boolean)
    fun setTextStrokeGradient(colors: IntArray, positions: FloatArray)
    fun setTextStroke(color:Int)
    fun setTextStrokeWidth(width:Float)
    fun drawTextViewCanvas(canvas: Canvas)
    fun drawTextViewCanvasAfter(canvas: Canvas)
}
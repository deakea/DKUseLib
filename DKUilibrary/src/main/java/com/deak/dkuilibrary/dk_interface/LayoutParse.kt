package com.deak.dkuilibrary.dk_interface

import android.graphics.Canvas
import android.view.View
import androidx.core.graphics.withSave

/**
 *@time 创建时间:2024/11/13
 *@user lxh
 *@version
 *@desc
 **/
interface LayoutParse {
    fun setLayoutSet()
    fun initParse(view:View)
    abstract fun setRadius(radius:Float)

    /**
     * 分别设置四个圆角
     *
     * @param leftTopRadius
     * @param rightTopRadius
     * @param rightBottomRadius
     * @param leftBottomRadius
     */
    fun setRadius(
        leftTopRadius: Float=0f,
        rightTopRadius: Float=0f,
        rightBottomRadius: Float=0f,
        leftBottomRadius: Float=0f,
    )
    /**
     * 设置渐变颜色，需要带入此方法
     */
    fun setGradientColor(colorArray: IntArray, positionArray: FloatArray,angle:Float)
    /**
     * 重新设置成单一颜色调用次方法
     */
    fun setSingleColor(color: Int)

    fun drawCanvas(canvas: Canvas)
}
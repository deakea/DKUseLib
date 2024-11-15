package com.deak.dkuilibrary.utils

import android.content.res.Resources
import android.graphics.PointF
import android.graphics.Rect
import android.graphics.RectF
import android.util.TypedValue
import kotlin.math.cos
import kotlin.math.sin


/**
 *@time 创建时间:2024/6/25
 *@user
 *@version
 *@desc
 **/
object DimensionUtils {

    fun Float.dp():Float{
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
    }
    fun Int.dp(): Float {
        return toFloat().dp()
    }
    fun Double.dp(): Float {
        return toFloat().dp()
    }
    fun Long.dp(): Long {
        return toFloat().dp().toLong()
    }
    fun Int?.toDKFloat():Float = this?.toFloat() ?: 0f
    fun Double?.toDKFloat():Float = this?.toFloat() ?: 0f
    fun String?.toDKFloat():Float = this?.toFloatOrNull() ?: 0f
    fun Float?.toDKInt():Int = this?.toInt() ?: 0
    fun Double?.toDKInt():Int = this?.toInt() ?: 0
    fun String?.toDKInt():Int = this?.toIntOrNull() ?: 0
    fun Int?.toDKDouble():Double = this?.toDouble() ?: 0.0
    fun Float?.toDKDouble():Double = this?.toDouble() ?: 0.0
    fun String?.toDKDouble():Double = this?.toDoubleOrNull() ?: 0.0
    fun Float?.toDKLong():Long = this?.toLong() ?: 0L
    fun Int?.toDKLong():Long = this?.toLong() ?: 0L
    fun Double?.toDKLong():Long = this?.toLong() ?: 0L
    fun String?.toDKLong():Long = this?.toLongOrNull() ?: 0L


    /**
     * 计算矩形的交点坐标
     *
     * @param angle 角度
     * @return 返回起点坐标和终点坐标
     */
    fun RectF.getAngleStartPoint(angle: Float): MutableList<PointF> {
        val angleDF = -angle+180
        val array = mutableListOf<PointF>()
        // 矩形中心点坐标
        val centerX = left + width() / 2
        val centerY = top + height() / 2

        // 根据角度计算焦点的坐标
        // cos(弧度)该角度在X轴方向上的投影长度与单位圆半径的比值
        // sin(弧度)该角度在Y轴方向上的投影长度与单位圆半径的比值
        val radians = Math.toRadians(angleDF.toDouble())
        val focalX = centerX + (width() / 2) * cos(radians)
        val focalY = centerY + (height() / 2) * sin(radians)
        array.add(PointF(focalX.toFloat(), focalY.toFloat()))
        //终点位置
        val radiansEnd = Math.toRadians(angleDF.toDouble()-180)
        val focalXEnd = centerX + (width() / 2) * cos(radiansEnd)
        val focalYEnd = centerY + (height() / 2) * sin(radiansEnd)
        array.add(PointF(focalXEnd.toFloat(), focalYEnd.toFloat()))
        return array
    }
    /**
     * 计算文字矩形的交点坐标
     *
     * @param angle 角度
     * @return 返回起点坐标和终点坐标
     */
    fun RectF.getTextAngleStartPoint(angle: Float,offX:Float=0f,offY:Float = 0f): MutableList<PointF> {
        val angleDF = -angle+180
        val array = mutableListOf<PointF>()
        // 矩形中心点坐标
        val centerX = left + width() / 2 + offX
        val centerY = top + height() / 2 + offY

        // 根据角度计算焦点的坐标
        // cos(弧度)该角度在X轴方向上的投影长度与单位圆半径的比值
        // sin(弧度)该角度在Y轴方向上的投影长度与单位圆半径的比值
        val radians = Math.toRadians(angleDF.toDouble())
        val focalX = centerX + (width() / 2) * cos(radians)
        val focalY = centerY + (height() / 2) * sin(radians)
        array.add(PointF(focalX.toFloat(), focalY.toFloat()))
        //终点位置
        val radiansEnd = Math.toRadians(angleDF.toDouble()-180)
        val focalXEnd = centerX + (width() / 2) * cos(radiansEnd)
        val focalYEnd = centerY + (height() / 2) * sin(radiansEnd)
        array.add(PointF(focalXEnd.toFloat(), focalYEnd.toFloat()))
        return array
    }
    /**
     * 计算文字矩形的交点坐标
     *
     * @param angle 角度
     * @return 返回起点坐标和终点坐标
     */
    fun Rect.getTextAngleStartPoint(angle: Float,offX:Float=0f,offY:Float = 0f): MutableList<PointF> {
        val angleDF = -angle+180
        val array = mutableListOf<PointF>()
        // 矩形中心点坐标
        val centerX = left + width() / 2 + offX
        val centerY = top + height() / 2 + offY

        // 根据角度计算焦点的坐标
        // cos(弧度)该角度在X轴方向上的投影长度与单位圆半径的比值
        // sin(弧度)该角度在Y轴方向上的投影长度与单位圆半径的比值
        val radians = Math.toRadians(angleDF.toDouble())
        val focalX = centerX + (width() / 2) * cos(radians)
        val focalY = centerY + (height() / 2) * sin(radians)
        array.add(PointF(focalX.toFloat(), focalY.toFloat()))
        //终点位置
        val radiansEnd = Math.toRadians(angleDF.toDouble()-180)
        val focalXEnd = centerX + (width() / 2) * cos(radiansEnd)
        val focalYEnd = centerY + (height() / 2) * sin(radiansEnd)
        array.add(PointF(focalXEnd.toFloat(), focalYEnd.toFloat()))
        return array
    }
}
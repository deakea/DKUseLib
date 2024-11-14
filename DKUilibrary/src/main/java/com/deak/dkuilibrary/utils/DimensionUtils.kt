package com.deak.dkuilibrary.utils

import android.content.res.Resources
import android.util.TypedValue
import kotlin.math.roundToLong

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
}
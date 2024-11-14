package com.deak.dkuilibrary.layout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.core.view.marginStart
import androidx.core.view.marginTop
import kotlin.math.max

/**
 *@time 创建时间:2024/6/25
 *@user
 *@version
 *@desc 标签功能、自动换行、自适应宽度
 **/
class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val childrenBounds = mutableListOf<Rect>()
    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //父view对于子view的要求、模式和具体的值
        var widthUsed = 0//当前
        var heightUsed = 0
        var lineWidthUsed = 0//当前行已使用的宽度
        var lineMaxHeight = 0
        val specWidthSize = MeasureSpec.getSize(widthMeasureSpec)
        val specWidthMode = MeasureSpec.getMode(widthMeasureSpec)
        for ((index, child) in children.withIndex()) {
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, heightUsed)
            if (lineWidthUsed+child.measuredWidth > specWidthSize && specWidthMode!= MeasureSpec.UNSPECIFIED){
                //父布不能是不限制子view宽度 如果下次绘制超过了父布局的宽度，实现换行
                lineWidthUsed = 0
                heightUsed += (lineMaxHeight)
                lineMaxHeight = 0
                measureChildWithMargins(child,widthMeasureSpec,0,heightMeasureSpec,heightUsed)
            }

            if (index >= childrenBounds.size) {
                childrenBounds.add(Rect())
            }
            val childBounds = childrenBounds[index]
            childBounds.set(
                lineWidthUsed+child.marginStart,
                heightUsed+child.marginTop,
                lineWidthUsed + child.measuredWidth+child.marginStart,
                heightUsed + child.measuredHeight+child.marginTop
            )
            lineWidthUsed += child.measuredWidth + child.marginStart + child.marginEnd//每次加上子view 的绘制宽度
//            lineMaxHeight = child.measuredHeight+child.marginTop+child.marginBottom
            widthUsed = max(widthUsed,lineWidthUsed)
            lineMaxHeight = max(lineMaxHeight, child.measuredHeight+child.marginTop+child.marginBottom)//高度取最高值
        }
        val selfWidth = widthUsed//保证为最宽一行的宽度
        val selfHeight = heightUsed + lineMaxHeight//当前最大高度加上已经使用的高度
        setMeasuredDimension(selfWidth, selfHeight)

    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        for ((index, child) in children.withIndex()) {
            val childBounds = childrenBounds[index]
            child.layout(
                childBounds.left, childBounds.top, childBounds.right, childBounds.bottom
            )
        }
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}
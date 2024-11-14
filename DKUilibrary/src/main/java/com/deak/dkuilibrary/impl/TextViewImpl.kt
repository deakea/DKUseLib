package com.deak.dkuilibrary.impl

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.core.graphics.withSave
import com.deak.dkuilibrary.R
import com.deak.dkuilibrary.dk_interface.TextViewInterface
import com.deak.dkuilibrary.utils.DimensionUtils.dp
import kotlin.math.floor

/**
 *@time 创建时间:2024/11/14
 *@user lxh
 *@version
 *@desc 自定义字体圆角阴影、渐变颜色，字体描边stroke等
 **/
class TextViewImpl(context: Context, attrs: AttributeSet?) : TextViewInterface {
    private var mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mShadowRadius = 0f
    private var mShadowY = 0f
    private var mShadowX = 0f
    private var mShadowColor = Color.parseColor("#33000000")
    private var isEnableTextShadow = false
    private var mTextBounds = Rect()
    private var mFontMetrics = FontMetrics()

    private var mColorArray = intArrayOf()
    private var mPositionArray = floatArrayOf()
    private var isUseGradient = false
    private lateinit var mTextView: TextView

    init {
        mTextPaint.style = Paint.Style.FILL
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.DKTextView)
        mShadowRadius = typedArray.getDimension(R.styleable.DKTextView_dk_textShadowRadius, 0.dp())
        mShadowX = typedArray.getDimension(R.styleable.DKTextView_dk_textShadowX, 0.dp())
        mShadowY = typedArray.getDimension(R.styleable.DKTextView_dk_textShadowY, 0.dp())
        mShadowColor = typedArray.getColor(
            R.styleable.DKTextView_dk_textShadowColor,
            Color.parseColor("#33000000")
        )
        isEnableTextShadow =
            typedArray.getBoolean(R.styleable.DKTextView_dk_enableTextShadow, false)
        isUseGradient = typedArray.getBoolean(R.styleable.DKTextView_dk_useGradientColor, false)
        if (isUseGradient) {
            mColorArray = intArrayOf(
                typedArray.getColor(R.styleable.DKTextView_dk_textStartColor, Color.TRANSPARENT),
                typedArray.getColor(R.styleable.DKTextView_dk_textEndColor, Color.TRANSPARENT)
            )
            mPositionArray = floatArrayOf(0f, 1f)
        }

        typedArray.recycle()
    }

    override fun initTextView(view: TextView) {
        mTextView = view
        mTextPaint.textSize = mTextView.textSize
        mTextPaint.color = mTextView.currentTextColor
    }

    override fun setTextBounds(content: String) {
        mTextPaint.getTextBounds(content, 0, content.length, mTextBounds)
        mTextPaint.getFontMetrics(mFontMetrics)
    }

    override fun setTextShadow(radius: Float, shadowX: Float, shadowY: Float) {
        mShadowRadius = radius
        mShadowX = shadowX
        mShadowY = shadowY
        if (radius > 0) {
            isEnableTextShadow = true
        }
    }

    override fun getShadowX(): Int = mShadowX.toInt()

    override fun getShadowY(): Int = mShadowY.toInt()

    override fun setTextGradientColor(colorArray: IntArray, positionArray: FloatArray) {

    }

    override fun drawTextViewCanvas(canvas: Canvas) {
        if (isEnableTextShadow) {
            mTextView.paint.setShadowLayer(mShadowRadius, mShadowX, mShadowY, mShadowColor)
        }
        if (isUseGradient) {
            val startX = mTextView.layout.getPrimaryHorizontal(0)
            val endX = startX + mTextBounds.right.toFloat()
            mTextView.paint.shader = LinearGradient(
                startX,
                0f,
                endX,
                0f,
                mColorArray,
                mPositionArray,
                Shader.TileMode.CLAMP
            )
        }

    }

    override fun drawTextViewCanvasAfter(canvas: Canvas) {

    }

}
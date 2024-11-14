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
import android.widget.TextView
import androidx.core.graphics.withSave
import com.deak.dkuilibrary.R
import com.deak.dkuilibrary.dk_interface.TextViewInterface
import com.deak.dkuilibrary.utils.DimensionUtils.dp

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

    private var mTextBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var isUseTextBorder = false

    init {
        mTextPaint.style = Paint.Style.FILL
        mTextBorderPaint.style = Paint.Style.STROKE
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
        isUseTextBorder = typedArray.getBoolean(R.styleable.DKTextView_dk_enableTextBorder, false)
        if (isUseGradient) {
            mColorArray = intArrayOf(
                typedArray.getColor(R.styleable.DKTextView_dk_textStartColor, Color.TRANSPARENT),
                typedArray.getColor(R.styleable.DKTextView_dk_textEndColor, Color.TRANSPARENT)
            )
            mPositionArray = floatArrayOf(0f, 1f)
        }
        if (isUseTextBorder){
            val borderWidth = typedArray.getDimension(R.styleable.DKTextView_dk_textBorderWidth, 0.dp())
            val borderColor = typedArray.getColor(R.styleable.DKTextView_dk_textBorderColor, Color.TRANSPARENT)
            mTextBorderPaint.strokeWidth = borderWidth
            mTextBorderPaint.color = borderColor

        }

        typedArray.recycle()
    }

    override fun initTextView(view: TextView) {
        mTextView = view
        mTextPaint.textSize = mTextView.textSize
        mTextPaint.color = mTextView.currentTextColor
        if (isUseTextBorder){
            mTextBorderPaint.textSize = mTextView.paint.textSize
            mTextBorderPaint.setTypeface(mTextView.typeface)
            mTextBorderPaint.flags = mTextView.paint.flags
            mTextBorderPaint.setAlpha(mTextView.paint.alpha)
        }
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
        if (isUseTextBorder){
            val textWidth = mTextBorderPaint.measureText(mTextView.text.toString())
            canvas.withSave {
                drawText(mTextView.text.toString(), (mTextView.width - textWidth) / 2,
                    mTextView.getBaseline().toFloat(),mTextBorderPaint)
            }
        }

    }

    override fun drawTextViewCanvasAfter(canvas: Canvas) {

    }

}
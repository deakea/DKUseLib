package com.deak.dkuilibrary.impl

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Paint.FontMetrics
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Shader
import android.text.TextPaint
import android.util.AttributeSet
import android.view.Gravity
import android.view.View.MeasureSpec
import android.view.ViewGroup
import android.widget.TextView
import android.widget.TextView.BufferType
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.TypedArrayUtils.getText
import androidx.core.graphics.withSave
import androidx.core.widget.addTextChangedListener
import com.deak.dkuilibrary.R
import com.deak.dkuilibrary.dk_interface.TextViewInterface
import com.deak.dkuilibrary.utils.DimensionUtils.dp
import com.deak.dkuilibrary.utils.DimensionUtils.getTextAngleStartPoint


/**
 *@time 创建时间:2024/11/14
 *@user lxh
 *@version
 *@desc 自定义字体圆角阴影、渐变颜色，字体描边stroke等
 **/
class TextViewImpl(context: Context, attrs: AttributeSet?) : TextViewInterface {
    private var mBorderColor: Int = 0
    private var mStrokeWidth: Float = 0f
    private var mShadowRadius = 0f
    private var mShadowY = 0f
    private var mShadowX = 0f
    private var mShadowColor = Color.parseColor("#33000000")
    private var isEnableTextShadow = false
    private var mTextBounds = Rect()
    private var mTextRectF = RectF()
    private var mBorderTextView: TextView =
        AppCompatTextView(context, attrs)

    private var mColorArray = intArrayOf()
    private var mPositionArray = floatArrayOf()
    private var isUseGradient = false
    private lateinit var mTextView: TextView

    private var isUseTextBorder = false
    private var mAngle = 0f
    private var mBorderAngle = 0f
    private var isTranGradient = false
    private var mStrokeShader : LinearGradient?=null
//    private var mTextBorderPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
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
        isTranGradient = typedArray.getBoolean(R.styleable.DKTextView_dk_isTranGradient, false)
        mAngle = typedArray.getFloat(R.styleable.DKTextView_dk_textGradientAngle, 0f)
        mBorderAngle = typedArray.getFloat(R.styleable.DKTextView_dk_textBorderGradientAngle, 0f)
        if (isUseGradient) {
            mColorArray = intArrayOf(
                typedArray.getColor(R.styleable.DKTextView_dk_textStartColor, Color.TRANSPARENT),
                typedArray.getColor(R.styleable.DKTextView_dk_textEndColor, Color.TRANSPARENT)
            )
            mPositionArray = floatArrayOf(0f, 1f)
        }
        if (isUseTextBorder) {
            val borderWidth =
                typedArray.getDimension(R.styleable.DKTextView_dk_textBorderWidth, 0.0f)
            val borderColor =
                typedArray.getColor(R.styleable.DKTextView_dk_textBorderColor, Color.TRANSPARENT)
            mStrokeWidth = borderWidth
            mBorderColor = borderColor

        }

        typedArray.recycle()
    }
    override fun initTextView(view: TextView) {
        mTextView = view
        if (isUseTextBorder) {
            val paint: Paint = mBorderTextView.paint
            paint.style = Paint.Style.STROKE
            paint.strokeWidth = mStrokeWidth
            mBorderTextView.includeFontPadding = false
            mBorderTextView.setTextColor(mBorderColor)
            mBorderTextView.gravity = mTextView.gravity
            mBorderTextView.layoutDirection = mTextView.layoutDirection
            mBorderTextView.textDirection = mTextView.textDirection
        }
        mTextView.addTextChangedListener {
            if (isUseTextBorder || isUseGradient || isTranGradient || isEnableTextShadow) {
                mTextView.invalidate()
            }
        }
    }
    override fun setDKLayoutParams(params: ViewGroup.LayoutParams) {
        mBorderTextView.layoutParams = params
    }

    override fun setDKTextDirection(textDirection: Int) {
        mBorderTextView.textDirection = textDirection
    }

    override fun setDKLayoutDirection(layoutDirection: Int) {
        mBorderTextView.layoutDirection = layoutDirection
    }

    override fun onDKMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int,superMeasure:()->Unit) {
        val text = mBorderTextView.text
        if (text == null || text != mTextView.text) {
            mBorderTextView.text = mTextView.text
            mTextView.postInvalidate()
        }
        superMeasure.invoke()
        val width = mTextView.measuredWidth
        val height = mTextView.measuredHeight
        val newWidth = (width + mStrokeWidth).toInt()
        val newHeight = (height + mStrokeWidth).toInt()
        // 增加一个宽度，以免显示不完全
        setDKMeasuredDimension(newWidth, newHeight)

        val newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(newWidth, MeasureSpec.EXACTLY)
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(newHeight, MeasureSpec.EXACTLY)
        mBorderTextView.measure(newWidthMeasureSpec, newHeightMeasureSpec)
    }
    override fun setDKMeasuredDimension(newWidth: Int, newHeight: Int){

    }
    override fun setBorderTextView(text: CharSequence?){
        if (text != null) {
            mBorderTextView.setText(text)
        }
    }

    override fun onDKLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        mBorderTextView.layout(left, top, right, bottom)
    }

    override fun setTextBounds(content: String) {

        mTextView.paint.getTextBounds(content, 0, content.length, mTextBounds)

        //获取文字所在区域
        mTextRectF = RectF(
            mTextView.layout.getPrimaryHorizontal(0),
            mTextView.layout.getLineBaseline(0).toFloat() - mTextView.textSize,
            if (mTextView.layout.lineCount>1) mTextView.layout.getLineRight(0) else mTextView.layout.getPrimaryHorizontal(content.length),
            mTextView.layout.getLineBaseline(mTextView.lineCount - 1).toFloat()
        )
    }

    override fun setTextShadow(radius: Float, shadowX: Float, shadowY: Float) {
        mShadowRadius = radius
        mShadowX = shadowX
        mShadowY = shadowY
        if (radius > 0) {
            isEnableTextShadow = true
        }
        mTextView.invalidate()
    }

    override fun getShadowX(): Int = mShadowX.toInt()

    override fun getShadowY(): Int = mShadowY.toInt()
    override fun isBorderText():Boolean = isUseTextBorder

    override fun setTextGradientColor(
        colorArray: IntArray,
        positionArray: FloatArray,
        angle: Float,
    ) {
        mAngle = angle
        isUseGradient = true
        mColorArray = colorArray
        mPositionArray = positionArray
        mTextView.invalidate()
    }

    override fun enableTextGradient(enable: Boolean) {
        isUseGradient = enable
        mTextView.invalidate()
    }

    override fun setTextStrokeGradient(colors: IntArray, positions: FloatArray) {
        if (colors.size >= 2 && positions.size >= 2) {

            val angleStartPoint = mTextRectF.getTextAngleStartPoint(mBorderAngle)
            mStrokeShader = LinearGradient(
                    angleStartPoint[0].x,
                    angleStartPoint[0].y,
                    angleStartPoint[1].x,
                    angleStartPoint[1].y, colors, positions, Shader.TileMode.CLAMP
                )
            mBorderTextView.paint.shader = mStrokeShader
            mTextView.invalidate()
        }
    }

    override fun setTextStroke(color: Int) {
        mStrokeShader = null
        mBorderColor = color
        mBorderTextView.paint.shader = mStrokeShader
        mTextView.invalidate()
    }

    override fun setTextStrokeWidth(width: Float) {
        isUseTextBorder = width > 0
        mStrokeWidth = width
        mTextView.invalidate()

    }

    private var mTrans = 0f
    override fun drawTextViewCanvas(canvas: Canvas) {
        if (isEnableTextShadow) {
            mTextView.paint.setShadowLayer(mShadowRadius, mShadowX, mShadowY, mShadowColor)
        }
        if (isUseGradient) {
            setTextBounds(mTextView.text.toString())
            val angleStartPoint = mTextRectF.getTextAngleStartPoint(mAngle)
            mTextView.paint.shader = LinearGradient(
                if (!isTranGradient) {
                    angleStartPoint[0].x
                } else {
                    -mTextRectF.width()
                },
                angleStartPoint[0].y,
                if (!isTranGradient) {
                    angleStartPoint[1].x
                } else {
                    0f
                },
                angleStartPoint[1].y,
                mColorArray,
                mPositionArray,
                Shader.TileMode.CLAMP
            ).apply {
                if (isTranGradient) {
                    val matrix = Matrix()
                    matrix.setTranslate(mTrans, 0f)
                    setLocalMatrix(matrix)
                    mTrans += 3
                    if (mTrans >= mTextRectF.width() * 2) {
                        mTrans = 0f
                    }
                }
            }
            if (isTranGradient) {
                mTextView.invalidate()
            }


        }


    }

    override fun drawTextViewCanvasAfter(canvas: Canvas) {

    }
    override fun setTextSuperCanvas(canvas: Canvas){
        if (isUseTextBorder) {
            // 复制原来TextViewg画笔中的一些参数
            if (mBorderTextView.layoutDirection != mTextView.layoutDirection) {
                mBorderTextView.layoutDirection = mTextView.layoutDirection
            }
            if (mBorderTextView.textDirection != mTextView.textDirection) {
                mBorderTextView.textDirection = mTextView.textDirection
            }
            mBorderTextView.paint.textSize = mTextView.paint.textSize
            mBorderTextView.draw(canvas)
            canvas.translate(mStrokeWidth / 2f, 0f)
        }
    }

}
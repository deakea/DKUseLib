package com.deak.dkuilibrary.impl

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PointF
import android.graphics.RectF
import android.graphics.Shader
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.graphics.withSave
import com.deak.dkuilibrary.R
import com.deak.dkuilibrary.dk_interface.LayoutParse
import com.deak.dkuilibrary.utils.DimensionUtils.dp
import com.deak.dkuilibrary.utils.DimensionUtils.getAngleStartPoint
import com.deak.dkuilibrary.utils.DimensionUtils.toDKFloat


/**
 *@time 创建时间:2024/11/13
 *@user lxh
 *@version
 *@desc
 **/
class CommonLayoutParse(context: Context, attrs: AttributeSet?) :LayoutParse{
    private var isUseGradientBg: Boolean = false
    private var strokeEnable: Boolean = false
    private var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mStokePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var mRadius: Float = 0f
    private var mTopLeftRadius: Float = 0f
    private var mTopRightRadius: Float = 0f
    private var mBottomLeftRadius: Float = 0f
    private var mBottomRightRadius: Float = 0f
    private var mPath = Path()
    private var mRectF = RectF()
    private var mStrokeRectF = RectF()
    private var mColorArray: IntArray = intArrayOf()
    private var mPositionArray = floatArrayOf(0.0f, 1f)
    private var mAngle: Float = 0f
    private var mStartEndGradientPoint = mutableListOf<PointF>()
    private lateinit var mView:View

    init {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundLayout)
        strokeEnable =
            typedArray.getBoolean(R.styleable.RoundLayout_round_strokeEnable, false)
        mPaint.style = Paint.Style.FILL
        mPaint.color =
            typedArray.getColor(R.styleable.RoundLayout_round_bgColor, Color.TRANSPARENT)
        mRadius = typedArray.getDimension(
            R.styleable.RoundLayout_round_layoutRadius,
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                0f,
                context.resources.displayMetrics
            )
        )
        mTopLeftRadius =
            typedArray.getDimension(R.styleable.RoundLayout_round_layoutTopLeftRadius, 0.dp())
        mTopRightRadius = typedArray.getDimension(
            R.styleable.RoundLayout_round_layoutTopRightRadius,
            0.dp()
        )
        mBottomLeftRadius = typedArray.getDimension(
            R.styleable.RoundLayout_round_layoutBottomLeftRadius,
            0.dp()
        )
        mBottomRightRadius = typedArray.getDimension(
            R.styleable.RoundLayout_round_layoutBottomRightRadius,
            0.dp()
        )
        //是否使用渐变背景
        isUseGradientBg =
            typedArray.getBoolean(R.styleable.RoundLayout_round_useGradientBg, false)
        if (isUseGradientBg) {
            mColorArray = intArrayOf(
                typedArray.getColor(
                    R.styleable.RoundLayout_round_gradientStartBg,
                    Color.TRANSPARENT
                ),
                typedArray.getColor(
                    R.styleable.RoundLayout_round_gradientEndBg,
                    Color.TRANSPARENT
                )
            )
        }



        if (strokeEnable) {
            mStokePaint.style = Paint.Style.STROKE
            mStokePaint.color =
                typedArray.getColor(R.styleable.RoundLayout_round_strokeColor, Color.BLACK)
            mStokePaint.strokeWidth = typedArray.getDimension(
                R.styleable.RoundLayout_round_strokeSize,
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP,
                    1f,
                    context.resources.displayMetrics
                )
            )
        }
        typedArray.recycle()
    }
    override fun initParse(view:View){
        mView = view
    }

    override fun setLayoutSet() {
        mView.apply {

            if (strokeEnable) {
                mRectF.set(
                    0f,
                    0f,
                    measuredWidth.toFloat(),
                    measuredHeight.toFloat()
                )
            } else {
                mRectF.set(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())
            }
            if (mRadius > 0) {
                mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW)
            } else {
                val rids = floatArrayOf(
                    mTopLeftRadius,
                    mTopLeftRadius,
                    mTopRightRadius,
                    mTopRightRadius,
                    mBottomRightRadius,
                    mBottomRightRadius,
                    mBottomLeftRadius,
                    mBottomLeftRadius,
                )
                mPath.addRoundRect(
                    RectF(0f, 0f, width.toFloat(), height.toFloat()),
                    rids,
                    Path.Direction.CW
                )
            }
            if (strokeEnable) {
                mStrokeRectF.set(
                    mStokePaint.strokeWidth / 2f,
                    mStokePaint.strokeWidth / 2f,
                    measuredWidth - mStokePaint.strokeWidth / 2f,
                    measuredHeight - mStokePaint.strokeWidth / 2f
                )
            }
            if (isUseGradientBg) {
                mStartEndGradientPoint = mRectF.getAngleStartPoint(mAngle)
                mPaint.shader = LinearGradient(
                    mStartEndGradientPoint[0].x, mStartEndGradientPoint[0].y, mStartEndGradientPoint[1].x, mStartEndGradientPoint[1].y,
                    mColorArray, mPositionArray, Shader.TileMode.CLAMP
                )
            }
        }
    }
    override fun setRadius(radius:Float){
        mRadius = radius
        mPath.addRoundRect(mRectF, mRadius, mRadius, Path.Direction.CW)
        mView.invalidate()
    }
    override fun setRadius(
        leftTopRadius: Float,
        rightTopRadius: Float,
        rightBottomRadius: Float,
        leftBottomRadius: Float,
    ) {
        mView.post {
            val rids = floatArrayOf(
                leftTopRadius,
                leftTopRadius,
                rightTopRadius,
                rightTopRadius,
                rightBottomRadius,
                rightBottomRadius,
                leftBottomRadius,
                leftBottomRadius
            )
            mPath.addRoundRect(
                RectF(0f, 0f, mView.width.toDKFloat(), mView.height.toDKFloat()),
                rids,
                Path.Direction.CW
            )
            mView.invalidate()
        }
    }

    /**
     * 设置渐变颜色，需要带入此方法
     */
    override fun setGradientColor(colorArray: IntArray, positionArray: FloatArray,angle:Float) {
        mView.post {
            mAngle = angle
            mStartEndGradientPoint = mRectF.getAngleStartPoint(mAngle)
            mPaint.shader = LinearGradient(
                0f, 0f, (mRectF.right), (mRectF.bottom),
                colorArray, positionArray, Shader.TileMode.REPEAT
            )
            mView.invalidate()
        }
    }

    /**
     * 重新设置成单一颜色调用次方法
     */
    override fun setSingleColor(color: Int) {
        mView.post {
            mPaint.shader = null
            mPaint.color = color
            mView.invalidate()
        }
    }

    override fun drawCanvas(canvas: Canvas) {
        canvas.withSave {
            canvas.clipPath(mPath)
            canvas.drawRect(mRectF, mPaint)
            if (strokeEnable) canvas.drawRoundRect(mStrokeRectF, mRadius, mRadius, mStokePaint)
        }
    }

}
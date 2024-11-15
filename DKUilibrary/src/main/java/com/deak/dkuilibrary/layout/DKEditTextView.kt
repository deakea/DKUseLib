package com.deak.dkuilibrary.layout

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.deak.dkuilibrary.dk_interface.LayoutParse
import com.deak.dkuilibrary.dk_interface.TextViewInterface
import com.deak.dkuilibrary.impl.CommonLayoutParse
import com.deak.dkuilibrary.impl.TextViewImpl

/**
 *@time 创建时间:2024/11/15
 *@user lxh
 *@version
 *@desc 自定义输入文本
 **/
class DKEditTextView(context: Context, attrs: AttributeSet?) : AppCompatEditText(context, attrs),
    LayoutParse by CommonLayoutParse(context, attrs),
    TextViewInterface by TextViewImpl(context, attrs) {
        init {
            initParse(this)
            initTextView(this)
            setBackgroundColor(Color.TRANSPARENT)
            includeFontPadding = false
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                setTextCursorDrawable(null)
            }
        }
}
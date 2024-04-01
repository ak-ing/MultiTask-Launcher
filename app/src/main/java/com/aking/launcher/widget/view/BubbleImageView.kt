package com.aking.launcher.widget.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Shader
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.aking.common.utils.ReflexMethod


class BubbleImageView : AppCompatImageView {
    private val mShader = LinearGradient(
        0f, 0f,
        0f, 1f,
        Color.parseColor("#A0000000"),
        Color.parseColor("#A0000000"),
        Shader.TileMode.CLAMP
    )

    private val mPaint = Paint().apply {
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }

    private val matrix = Matrix()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun setPressed(pressed: Boolean) {
        super.setPressed(pressed)
        postInvalidate()
    }

    override fun onDraw(canvas: Canvas) {
        var bottomSaveCount: Int? = null
        if (isPressed) {
            matrix.reset()
            matrix.setScale(1f, height.toFloat())
            mShader.setLocalMatrix(matrix)
            mPaint.shader = mShader

            bottomSaveCount = ReflexMethod("saveUnclippedLayer")
                .target(canvas).setupClass(Canvas::class.java)
                .paramClasses(
                    Int::class.java,
                    Int::class.java,
                    Int::class.java,
                    Int::class.java
                )
                .params(
                    0,
                    0,
                    width,
                    height
                ).call<Int>()
        }
        super.onDraw(canvas)
        if (isPressed) {
            bottomSaveCount?.also {
                ReflexMethod("restoreUnclippedLayer")
                    .target(canvas).setupClass(Canvas::class.java)
                    .paramClasses(Int::class.java, Paint::class.java)
                    .params(it, mPaint).call<Void>()
            }
        }
    }
}
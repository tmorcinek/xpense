package com.morcinek.xpense.home.overview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import android.view.View

/**
 * Copyright 2016 Tomasz Morcinek. All rights reserved.
 */
class RingChart(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    private val rect = RectF()
    private val backgroundRect = RectF()
    private val paint = Paint()

    var values: Iterable<Pair<Int, Float>> = listOf()
        set(value) {
            field = value
            invalidate()
        }

    var ringSize = 0.15f
        set(value) {
            if (value in 0..1) {
                field = value
                invalidate()
            }
        }

    init {
        paint.setStyle(Paint.Style.FILL_AND_STROKE)
    }

    override protected fun onSizeChanged(width: Int, height: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(width, height, oldw, oldh)
        val size = Math.min(width.toFloat(), height.toFloat())
        rect.set(0f, 0f, size, size)
        backgroundRect.set(size * ringSize, size * ringSize, size * (1 - ringSize), size * (1 - ringSize))
    }

    override protected fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawValues(canvas)
        drawInnerCycle(canvas)
    }

    private fun drawValues(canvas: Canvas) {
        var start = -90f
        values.forEach {
            paint.color = it.first
            val shift = 360 * it.second
            canvas.drawArc(rect, start, shift, true, paint)
            start += shift
        }
    }

    private fun drawInnerCycle(canvas: Canvas) {
        paint.color = getBackgroundColor()
        canvas.drawArc(backgroundRect, 0f, 360f, true, paint)
    }

    private fun getBackgroundColor() =
            when (background) {
                is ColorDrawable -> (background as ColorDrawable).color
                else -> Color.WHITE
            }
}

package ru.startandroid.develop.p1501pathmeasure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.view.View

//Выделим часть одного Path в другой Path.
class DrawViewExm4(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    val path = Path().apply {
        moveTo(100F, 300F)
        rLineTo(150F, 150F)
        rLineTo(150F, -100F)
        rQuadTo(150F, 200F, 300F, 0F)
        rLineTo(150F, 100F)
        rLineTo(150F, -150F)
    }

    val pMeasure = PathMeasure(path, false)

    val path1 = Path()

    init {
        pMeasure.getSegment(150F, 850F, path1, true)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawPath(path1, paint)
        } ?: return
    }
}
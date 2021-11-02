package ru.startandroid.develop.p1491canvastext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class DrawViewExm1(context: Context) : View(context) {
    val fontSize = 100F
    val text = "Test width text"

    val redPaint = Paint().apply {
        color = Color.RED
    }
    //настраиваем paint для вывода текста
    val fontPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = fontSize
        style = Paint.Style.STROKE
    }

    //ширина текста
    val mWidth = fontPaint.measureText(text)

    //посимвольная ширина
    val widths = FloatArray(text.length)

    init {
        /*
            getTextWidths позволяет получить массив, содержащий значения ширины для каждого
                символа текста
         */
        fontPaint.getTextWidths(text, widths)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            translate(50F, 250F)
            //вывод текста
            drawText(text, 0F, 0F, fontPaint)
            //линия шириной в текст
            drawLine(0F, 0F, mWidth, 0F, fontPaint)
            //посимвольные красные точки
            drawCircle(0F, 0F, 3F, redPaint)
            for (w in widths) {
                translate(w, 0F)
                drawCircle(0F, 0F, 3F, redPaint)
            }
        } ?: return
    }
}
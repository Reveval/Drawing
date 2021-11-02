package ru.startandroid.develop.p1491canvastext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.view.View

//Рассмотрим еще несколько методов форматирования текста.
class DrawViewExm4(context: Context) : View(context) {
    val fontSize = 60F
    val text = "Test width text"
    val mY = 80F

    val p = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = fontSize
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //обычный текст
            translate(50F, mY)
            drawText(text, 0F, 0F, p)

            //растянутый
            translate(0F, mY)
            p.textScaleX = 1.5F
            drawText(text, 0F, 0F, p)
            p.textScaleX = 1F

            //наклоненный
            translate(0F, mY)
            p.textSkewX = 0.5F
            drawText(text, 0F, 0F, p)
            p.textSkewX = 0F

            //подчеркнутый
            translate(0F, mY)
            p.isUnderlineText = true
            drawText(text, 0F, 0F, p)
            p.isUnderlineText = false

            //зачеркнутый
            translate(0F, mY)
            p.isStrikeThruText = true
            drawText(text, 0F, 0F, p)
            p.isStrikeThruText = false
        } ?: return
    }
}
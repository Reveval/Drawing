package ru.startandroid.develop.p1491canvastext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class DrawViewExm2(context: Context) : View(context) {
    private val fontSize = 80F
    private val mText = "Test width text"
    private val maxWidth = 350F


    val p = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = fontSize
    }

    //количество символов и их ширина
    val measuredWidth = FloatArray(1)
    /*
        Метод breakText позволяет нам узнать сколько символов текста поместится в
            указанную нами ширину. На вход ему передаем:
                - текст
                - true, означает что пойдем по тексту вперед, начиная с первого
                    символа. Если false, то пойдем с конца.
                - ширину, которая будет ограничивать текст
                - массив, для получения точного значения ширины
            Метод breakText возвращает кол-во символов.
     */
    val cnt = p.breakText(mText, true, maxWidth, measuredWidth)

    private val realWidth = measuredWidth[0]

    private val info = "cnt = $cnt, realWidth = $realWidth, maxWidth = $maxWidth"

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //данные о ширине
            p.textSize = 24F
            drawText(info, 50F, 50F, p)

            //текст
            p.textSize = fontSize
            drawText(mText, 50F, 250F, p)

            p.strokeWidth = 10F

            //полоса реальной ширины урезанного текста
            p.color = Color.BLUE
            drawLine(50F, 260F, 50F + realWidth, 260F, p)

            //полоса лимита
            p.color = Color.GREEN
            drawLine(50F, 270F, 50F + maxWidth, 270F, p)
        } ?: return
    }

}
package ru.startandroid.develop.p1461canvastransform

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

//Мы можем запоминать состояние матрицы канвы и потом восстанавливать его.
class DrawViewExm2(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val rectF1 = RectF(50F, 50F, 100F, 100F)
    val rectF2 = RectF(50F, 150F, 100F, 200F)

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            p.color = Color.GREEN
            drawRect(rectF1, p)

            //преобразование канвы и рисование зеленых квадратов
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)

            //сохраняем настройки матрицы канвы
            save()

            //преобразование канвы и рисование красных квадратов
            p.color = Color.RED
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)

            //возврат канвы к предыдущему сохранению
            restore()

            //синий квадрат
            p.color = Color.BLUE
            drawRect(rectF2, p)
        } ?: return
    }
}
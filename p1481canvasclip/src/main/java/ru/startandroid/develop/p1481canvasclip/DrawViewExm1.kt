package ru.startandroid.develop.p1481canvasclip

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

/*
    Используем Clip. Он на канве определяет область, где рисование будет работать. А в остальных
        местах ничего рисоваться не будет.
 */
class DrawViewExm1(context: Context) : View(context) {
    val p = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }
    val rect = Rect(210, 210, 410, 510)

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //сетка
            p.color = Color.BLUE
            drawGrid(this)

            //красный прямоугольник
            p.color = Color.RED
            drawRect(rect, p)

            //смещение
            translate(600F, 0F)

            /*
                задание clip-области. методом clipRect говорим канве, что теперь рисование
                    доступно только в этой области.
             */
            clipRect(rect)

            //сетка
            p.color = Color.BLUE
            drawGrid(this)
        } ?: return
    }

    private fun drawGrid(canvas: Canvas) {
        for (i in 25 until 400 step 25) {
            canvas.drawLine(100F + i, 100F, 100F + i, 600F, p)
        }

        for (i in 25 until 500 step 25) {
            canvas.drawLine(100F, 100F + i, 500F, 100F + i, p)
        }
    }
}
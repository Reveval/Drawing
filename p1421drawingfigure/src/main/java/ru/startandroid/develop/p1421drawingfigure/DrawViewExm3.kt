package ru.startandroid.develop.p1421drawingfigure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class DrawViewExm3(context: Context) : View(context) {
    val p = Paint()
    val rect = Rect(100, 200, 200, 300)

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        p.apply {
            color = Color.BLUE
            strokeWidth = 10F
            textSize = 30F

            //создаем строку с значениями ширины и высоты канвы
            val message = "width = $width, height = $height"
            canvas.drawText(message, 100F, 100F, p)

            //перенастраиваем кисть на заливку
            style = Paint.Style.FILL
            canvas.drawRect(rect, p)

            //перенастраиваем кисть на контуры
            style = Paint.Style.STROKE
            rect.offset(150, 0)
            canvas.drawRect(rect, p)

            //перенастраивам кисть на заливку + контуры
            style = Paint.Style.FILL_AND_STROKE
            rect.offset(150, 0)
            canvas.drawRect(rect, p)
        }
    }
}
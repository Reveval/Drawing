package ru.startandroid.develop.p1431drawingpath

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.View

class DrawViewExm3(context: Context) : View(context) {
    val p = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 1F
        textSize = 20F
    }
    val path = Path()
    val text = "Draw the text, with origin at (x,y), using the specified paint"

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        //черный
        path.run {
            reset()
            addCircle(200F, 200F, 100F, Path.Direction.CW)
            p.color = Color.BLACK
            canvas.drawTextOnPath(text, this, 0F, 0F, p)
        }

        p.apply {
            path.reset()
            //для будущего синего круга задаем направление против часовой стрелки
            path.addCircle(500F, 200F, 100F, Path.Direction.CCW)
            //синий
            style = Paint.Style.FILL
            color = Color.BLUE
            canvas.drawTextOnPath(text, path, 0F, 0F, this)
            style = Paint.Style.STROKE
            canvas.drawPath(path, this)

            //зеленый
            path.offset(-300F, 250F)
            style = Paint.Style.FILL
            color = Color.GREEN
            canvas.drawTextOnPath(text, path, 100F, 0F, this)
            style = Paint.Style.STROKE
            canvas.drawPath(path, this)

            //красный
            path.offset(300F, 0F)
            style = Paint.Style.FILL
            color = Color.RED
            canvas.drawTextOnPath(text, path, 0F, 30F, this)
            style = Paint.Style.STROKE
            canvas.drawPath(path, this)
        }
    }
}
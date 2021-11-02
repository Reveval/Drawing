package ru.startandroid.develop.p1441matrixtransform

import android.content.Context
import android.graphics.*
import android.view.View

//Поворот фигуры при помощи Matrix
class DrawViewExm3(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path()
    val mMatrix = Matrix()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        path.apply {
            //создаем крест
            reset()
            addRect(300F, 150F, 450F, 200F, Path.Direction.CW)
            addRect(350F, 100F, 400F, 250F, Path.Direction.CW)
            addCircle(375F, 125F, 5F, Path.Direction.CW)

            //рисуем path зеленым
            p.color = Color.GREEN
            canvas.drawPath(this, p)

            //настраиваем матрицу на поворот на 120 градусов относительно точки (600, 400)
            mMatrix.reset()
            mMatrix.setRotate(120F, 600F, 400F)

            //применяем матрицу к path
            transform(mMatrix)

            //рисуем path синим
            p.color = Color.BLUE
            canvas.drawPath(this, p)

            //рисуем точку, отночительно которой был выполнен поворот
            p.color = Color.BLACK
            canvas.drawCircle(600F, 400F, 5F, p)
        }
    }
}
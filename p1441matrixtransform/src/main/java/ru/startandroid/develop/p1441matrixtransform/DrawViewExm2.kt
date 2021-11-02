package ru.startandroid.develop.p1441matrixtransform

import android.content.Context
import android.graphics.*
import android.view.View

//изменение размера при помощи matrix
class DrawViewExm2(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path()
    val mMatrix = Matrix()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        path.run {
            //создаем крест в path
            reset()
            addRect(300F, 150F, 450F, 200F, Path.Direction.CW)
            addRect(350F, 100F, 400F, 250F, Path.Direction.CW)

            //рисуем path зеленым
            p.color = Color.GREEN
            canvas.drawPath(this, p)

            /*
                настраиваем матрицу на изменение размера:
                    - в 2 раза по горизонтали
                    - в 2,5 по вертикали
                    относительно точки (375, 100)
             */
            mMatrix.reset()
            mMatrix.setScale(2F, 2.5F, 375F, 100F)

            //применяем матрицу к path
            transform(mMatrix)

            //рисуем path синим
            p.color = Color.BLUE
            canvas.drawPath(this, p)

            //рисуем точку относительно которой было выполнено преобразование
            p.color = Color.BLACK
            canvas.drawCircle(375F, 100F, 5F, p)
        }
    }
}
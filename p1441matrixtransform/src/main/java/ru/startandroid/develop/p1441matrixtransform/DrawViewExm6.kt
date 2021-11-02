package ru.startandroid.develop.p1441matrixtransform

import android.content.Context
import android.graphics.*
import android.view.View

class DrawViewExm6(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path()
    val mMatrix = Matrix()
    val rectF = RectF(100F, 100F, 200F, 200F)
    val rectFDst = RectF()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        //прямоугольник
        path.run {
            reset()
            addRect(rectF, Path.Direction.CW)
            p.color = Color.BLACK
            canvas.drawPath(this, p)
        }

        //поворот, размер и перемещение
        mMatrix.apply {
            setRotate(45F, 150F, 150F)
            postScale(1.2F, 0.8F, 150F, 150F)
            postTranslate(200F, 0F)
            path.transform(this)
        }

        //итогая фигура зеленым цветом
        p.color = Color.GREEN
        canvas.drawPath(path, p)

        /*
            границы, полученные от изначального прямоугольника. mapRect – возьмет на вход
                прямоугольник, выполнит для него преобразование и вернет прямоугольник,
                составляющий границы получившейся фигуры.
         */
        mMatrix.mapRect(rectFDst, rectF)
        p.color = Color.BLUE
        canvas.drawRect(rectFDst, p)
    }
}
package ru.startandroid.develop.p1461canvastransform

import android.content.Context
import android.graphics.*
import android.view.View

class DrawViewExm1(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val rectF = RectF(100F, 100F, 200F, 200F)
    val mMatrix = Matrix()
    val path = Path()

    override fun onDraw(canvas: Canvas?) {
        /*canvas?.apply {
            //рисуем квадрат
            path.reset()
            path.addRect(rectF, Path.Direction.CW)
            p.color = Color.BLACK
            drawPath(path, p)

            //преобразованный квадрат
            mMatrix.reset()
            mMatrix.preRotate(30F)
            mMatrix.preTranslate(500F, 0F)
            path.transform(mMatrix)
            p.color = Color.BLUE
            drawPath(path, p)
        } ?: return*/

        //теперь делаем тоже самое, но с помощью канвы
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //квадрат
            p.color = Color.BLACK
            drawRect(rectF, p)
            /*
                квадрат на канве с преобразованиями. Методы канвы аналогичны pre-методам матрицы.
                    Матрица канвы настроена, теперь все объекты, которые мы будем рисовать на
                    канве, будут преобразованы согласно ее матрице.
             */
            rotate(30F)
            translate(500F, 000F)
            p.color = Color.GREEN
            drawRect(rectF, p)
        } ?: return
    }
}
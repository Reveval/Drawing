package ru.startandroid.develop.p1451matrixtransform2

import android.content.Context
import android.graphics.*
import android.view.View

/*
    воспользуемся двумя точками в методе setPolyToPoly. В результате две точки позволят нам
        задать перемещение, поворот и изменение размера исходной фигуры.
 */
class DrawViewExm3(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val pBlack = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3F
    }

    val path = Path()
    val pathDst = Path()
    val mMatrix = Matrix()

    val rectF = RectF(100F, 100F, 200F, 200F)
    val src = arrayOf(100F, 100F, 200F, 200F).toFloatArray()
    val dst = arrayOf(50F, 300F, 250F, 500F).toFloatArray()
    val dst2 = arrayOf(400F, 200F, 500F, 200F).toFloatArray()

    val points = 2

    override fun onDraw(canvas: Canvas?) {
        //зеленый квадрат
        canvas?.apply {
            //зеленый квадрат
            path.reset()
            path.addRect(rectF, Path.Direction.CW)
            p.color = Color.GREEN
            drawPath(path, p)
            drawLine(src[0], src[1], src[2], src[3], pBlack)

            //синий квадрат
            //преобразование
            mMatrix.setPolyToPoly(src, 0, dst, 0, points)
            path.transform(mMatrix, pathDst)
            //рисование
            p.color = Color.BLUE
            drawPath(pathDst, p)
            drawLine(dst[0], dst[1], dst[2], dst[3], pBlack)

            //красный квадрат
            //преобразование
            mMatrix.setPolyToPoly(src, 0, dst2, 0, points)
            path.transform(mMatrix, pathDst)
            //рисование
            p.color = Color.RED
            drawPath(pathDst, p)
            drawLine(dst2[0], dst2[1], dst2[2], dst2[3], pBlack)
        } ?: return
    }
}
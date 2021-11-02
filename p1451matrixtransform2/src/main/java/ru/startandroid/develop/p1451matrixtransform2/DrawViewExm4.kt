package ru.startandroid.develop.p1451matrixtransform2

import android.content.Context
import android.graphics.*
import android.view.View

/*
    рассмотрим пример с тремя точками. В результате, Три точки (массив из шести координат)
        позволяют нам задать перемещение, поворот, изменение размера и наклон.
 */
class DrawViewExm4(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val pGray = Paint().apply {
        color = Color.GRAY
        strokeWidth = 3F
    }

    val pBlack = Paint().apply {
        color = Color.BLACK
        strokeWidth = 3F
    }

    val path = Path()
    val pathDst = Path()
    val mMatrix = Matrix()

    val rectF = RectF(100F, 100F, 200F, 200F)
    val src = arrayOf(100F, 100F, 200F, 200F, 200F, 100F).toFloatArray()
    val dst = arrayOf(50F, 300F, 250F, 500F, 230F, 350F).toFloatArray()
    val dst2 = arrayOf(400F, 200F, 500F, 200F, 440F, 100F).toFloatArray()

    val points = 3

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        canvas.apply {
            //зеленый квадрат
            path.reset()
            path.addRect(rectF, Path.Direction.CW)
            p.color = Color.GREEN
            drawPath(path, p)
            drawLine(src[0], src[1], src[2], src[3], pBlack)
            drawLine(src[0], src[1], src[4], src[5], pGray)

            //синий квадрат
            //преобразование
            mMatrix.setPolyToPoly(src, 0, dst, 0, points)
            path.transform(mMatrix, pathDst)
            //рисование
            p.color = Color.BLUE
            drawPath(pathDst, p)
            drawLine(dst[0], dst[1], dst[2], dst[3], pBlack)
            drawLine(dst[0], dst[1], dst[4], dst[5], pGray)

            //красный квадрат
            //преобразование
            mMatrix.setPolyToPoly(src, 0, dst2, 0, points)
            path.transform(mMatrix, pathDst)
            //рисование
            p.color = Color.RED
            drawPath(pathDst, p)
            drawLine(dst2[0], dst2[1], dst2[2], dst2[3], pBlack)
            drawLine(dst2[0], dst2[1], dst2[4], dst2[5], pGray)
        }
    }
}
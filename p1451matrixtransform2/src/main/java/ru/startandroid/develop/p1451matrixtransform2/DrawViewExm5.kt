package ru.startandroid.develop.p1451matrixtransform2

import android.content.Context
import android.graphics.*
import android.view.View

/*
    Смотрим пример с четырьмя точками. В результате, четыре точки (массив из восьми координат)
        позволяют нам задать перемещение, поворот, изменение размера, наклон и перспективу.
 */
class DrawViewExm5(context: Context) : View(context) {
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

    val pWhite = Paint().apply {
        color = Color.WHITE
        strokeWidth = 3F
    }

    val path = Path()
    val pathDst = Path()
    val mMatrix = Matrix()

    val rectF = RectF(100F, 100F, 200F, 200F)
    val src = arrayOf(100F, 100F, 200F, 200F, 200F, 100F, 100F, 200F).toFloatArray()
    val dst = arrayOf(50F, 300F, 250F, 500F, 230F, 350F, 40F, 550F).toFloatArray()
    val dst2 = arrayOf(400F, 200F, 500F, 200F, 440F, 100F, 440F, 230F).toFloatArray()

    val points = 4

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //зеленый квадрат
            path.reset()
            path.addRect(rectF, Path.Direction.CW)
            p.color = Color.GREEN
            drawPath(path, p)
            drawLine(src[0], src[1], src[2], src[3], pBlack)
            drawLine(src[0], src[1], src[4], src[5], pGray)
            drawLine(src[0], src[1], src[6], src[7], pWhite)

            //синий квадрат
            //преобразование
            mMatrix.setPolyToPoly(src, 0, dst, 0, points)
            path.transform(mMatrix, pathDst)
            //рисование
            p.color = Color.BLUE
            drawPath(pathDst, p)
            drawLine(dst[0], dst[1], dst[2], dst[3], pBlack)
            drawLine(dst[0], dst[1], dst[4], dst[5], pGray)
            drawLine(dst[0], dst[1], dst[6], dst[7], pWhite)

            //красный квадрат
            //преобразование
            mMatrix.setPolyToPoly(src, 0, dst2, 0, points)
            path.transform(mMatrix, pathDst)
            //рисование
            p.color = Color.RED
            drawPath(pathDst, p)
            drawLine(dst2[0], dst2[1], dst2[2], dst2[3], pBlack)
            drawLine(dst2[0], dst2[1], dst2[4], dst2[5], pGray)
            drawLine(dst2[0], dst2[1], dst2[6], dst2[7], pWhite)
        } ?: return
    }
}
package ru.startandroid.develop.p1511patheffect

import android.content.Context
import android.graphics.*
import android.view.View

/*
    PathDashPathEffect
        PathDashPathEffect позволяет сделать пунктирную линию, но в качестве пунктира можно
        использовать свой Path-объект.
 */
class DrawViewExm4(context: Context) : View(context) {
    val path = Path().apply {
        addRect(-100F, 0F, 100F, 500F, Path.Direction.CW)
    }

    //Создаем объект pathStamp в виде стрелки
    val pathStamp = Path().apply {
        lineTo(-10F, -10F)
        lineTo(10F, 0F)
        lineTo(-10F, 10F)
        close()
    }


    val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 20F
    }

    /*
         На кисти вешаем эффект PathDashPathEffect. Его конструктор на вход принимает:

            - path-объект, который будет использован в качестве пунктира
            - расстояние между пунктирами
            - отступ от начала
            - стиль эффекта:

            PathDashPathEffect.Style.MORPH – срезает пунктир на углах
            PathDashPathEffect.Style.ROTATE – корректно работает с углами
            PathDashPathEffect.Style.TRANSLATE – не поворачивает pathStamp по направлению
                основной линии
     */
    val greenPaintWithPathDash = Paint(mainPaint).apply {
        color = Color.GREEN
        pathEffect = PathDashPathEffect(pathStamp, 20F, 0F,
            PathDashPathEffect.Style.MORPH)
    }

    val bluePaintWithPathDash = Paint(mainPaint).apply {
        color = Color.BLUE
        pathEffect = PathDashPathEffect(pathStamp, 20F, 0F,
            PathDashPathEffect.Style.ROTATE)
    }

    val redPaintWithPathDash = Paint(mainPaint).apply {
        color = Color.RED
        pathEffect = PathDashPathEffect(pathStamp, 20F, 10F,
            PathDashPathEffect.Style.TRANSLATE)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            translate(120F, 100F)
            drawPath(path, mainPaint)

            translate(250F, 0F)
            drawPath(path, greenPaintWithPathDash)

            translate(250F, 0F)
            drawPath(path, bluePaintWithPathDash)

            translate(250F, 0F)
            drawPath(path, redPaintWithPathDash)
        } ?: return
    }
}
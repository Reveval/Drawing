package ru.startandroid.develop.p1511patheffect

import android.content.Context
import android.graphics.*
import android.view.View

/*
    DiscretePathEffect
        DiscretePathEffect позволяет получить ломанную линию из прямой. Полученная ломанная
        линия будет состоять из фрагментов, а мы можем повлиять на длину этих фрагментов
        (первый параметр конструктора) и степень излома (второй параметр).
 */
class DrawViewExm2(context: Context) : View(context) {
    val path = Path().apply {
        rLineTo(100F, 300F)
        rLineTo(100F, -100F)
        rLineTo(100F, 300F)
    }

    //создаем черную кисть без эффектов
    val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    //создаем зеленую кисть с степенью излома 5 и длиной фрагментов 10
    val paintWithDiscrete5 = Paint(mainPaint).apply {
        color = Color.GREEN
        pathEffect = DiscretePathEffect(10F, 5F)
    }

    //создаем синию кисть с степенью излома 15 и длиной фрагментов 10
    val paintWithDiscrete15 = Paint(mainPaint).apply {
        color = Color.BLUE
        pathEffect = DiscretePathEffect(10F, 15F)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            translate(100F, 100F)
            drawPath(path, mainPaint)

            translate(250F, 0F)
            drawPath(path, paintWithDiscrete5)

            translate(250F, 0F)
            drawPath(path, paintWithDiscrete15)
        } ?: return
    }
}
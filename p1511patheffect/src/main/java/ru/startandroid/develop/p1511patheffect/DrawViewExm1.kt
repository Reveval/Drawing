package ru.startandroid.develop.p1511patheffect

import android.content.Context
import android.graphics.*
import android.view.View

/*
    У класса PathEffect есть несколько наследников, которые позволяют влиять на рисуемые нами
        объекты. Рассмотрим на примерах их использование.

    CornerPathEffect
        Эффект CornerPathEffect просто закругляет углы. На вход принимает радиус закругления.
 */
class DrawViewExm1(context: Context) : View(context) {
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

    //создаем зеленую кисть с закруглением радиусом в 25
    val paintWithCorner25 = Paint(mainPaint).apply {
        color = Color.GREEN
        pathEffect = CornerPathEffect(25F)
    }

    //создаем зеленую кисть с закруглением радиусом в 25
    val paintWithCorner50 = Paint(mainPaint).apply {
        color = Color.BLUE
        pathEffect = CornerPathEffect(50F)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            translate(100F, 100F)
            drawPath(path, mainPaint)

            translate(250F, 0F)
            drawPath(path, paintWithCorner25)

            translate(250F, 0F)
            drawPath(path, paintWithCorner50)
        } ?: return
    }
}
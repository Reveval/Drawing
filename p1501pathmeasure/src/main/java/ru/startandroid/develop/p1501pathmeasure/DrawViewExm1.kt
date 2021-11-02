package ru.startandroid.develop.p1501pathmeasure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PathMeasure
import android.view.View

const val LOG_TAG = "myLogs"

/*
    Рассмотрим PathMeasure, весьма полезный в некоторых случаях инструмент, который умеет:
        - вычислять длину сегментов Path
        - определять, закрыт или открыт сегмент
        - получать координаты и угол наклона для указанной точки Path
        - выделять часть Path в отдельный объект
 */
class DrawViewExm1(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 30F
    }

    //создаем Path, состоящий из нескольких линий и одной кривой
    val path = Path().apply {
        moveTo(100F, 300F)
        rLineTo(150F, 100F)
        rLineTo(150F, -100F)
        rQuadTo(150F, 200F, 300F, 0F)
        rLineTo(150F, 100F)
        rLineTo(150F, -100F)
    }

    /*
        Далее создаем для него PathMeasure, флаг forceClosed при этом ставим false – нам не нужно
            закрывать Path.
     */
    val pMeasure = PathMeasure(path, false)

    //Методом getLength получаем длину Path.
    val length = pMeasure.length

    //В onDraw рисуем Path и выводим на экран его длину.
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawPath(path, paint)
            drawText("Length: $length", 100F, 100F, paintText)
        } ?: return
    }
}
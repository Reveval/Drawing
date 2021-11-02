package ru.startandroid.develop.p1501pathmeasure

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.View

class DrawViewExm3(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    /*
        При создании Path мы используем методы moveTo и rMoveTo. Эти методы начинают новый контур
            в Path. Таким образом у нас получилось три контура. Методом close мы закроем второй
            и третий контур, первый оставим открытым.
     */
    val path = Path().apply {
        moveTo(100F, 300F)
        rLineTo(150F, 150F)
        rLineTo(150F, -100F)

        moveTo(0F, 0F)
        rQuadTo(150F, 200F, 300F, 0F)
        close()

        rMoveTo(0F, 0F)
        rLineTo(150F, 100F)
        rLineTo(150F, -150F)
        close()
    }

    /*
        Далее создаем для него PathMeasure, флаг forceClosed при этом ставим false – нам не нужно
            закрывать Path.
     */
    val pMeasure = PathMeasure(path, false)

    /*
        Далее используем nextContour, чтобы перебирать контуры и методами getLength и isClosed
            получаем длину и выясняем закрыт ли контур. Выводим в лог всю эту информацию.
     */
    init {
        do {
            Log.d(LOG_TAG, "Length: ${pMeasure.length}, isClosed: ${pMeasure.isClosed}")
        } while (pMeasure.nextContour())
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawPath(path, paint)
        } ?: return
    }
}
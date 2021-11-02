package ru.startandroid.develop.p1471region

import android.content.Context
import android.graphics.*
import android.view.View

//Рассмотрим еще несколько полезных методов региона.
class DrawViewExm2(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    //path, треугольник
    val path = Path().apply {
        moveTo(100F, 100F)
        lineTo(150F, 150F)
        lineTo(100F, 200F)
        close()
    }

    //регион из прямоугольника обрезки
    val rect = Rect(100, 100, 150, 150)
    val clipRegion = Region(rect)

    //итоговый регион
    val region = Region()

    //получаем path из региона
    val pathDst = region.run {
        //отсекаем от path область clipRegion
        setPath(path, clipRegion)
        region.boundaryPath
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //треугольник
            p.color = Color.GREEN
            drawPath(path, p)

            translate(200F, 0F)

            //верхняя часть треугольника
            p.color = Color.BLUE
            drawPath(pathDst, p)
        } ?: return
    }

}
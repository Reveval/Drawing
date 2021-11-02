package ru.startandroid.develop.p1471region

import android.content.Context
import android.graphics.*
import android.view.View

/*
    Используем Region. Region - это объект, который позволяет нам совмещать несколько фигур в
        одну, используя различные режимы: объединение, пересечение и пр.
 */
class DrawViewExm1(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
    }

    //прямоугольники
    val rect1 = Rect(200, 200, 400, 400)
    val rect2 = Rect(300, 300, 500, 500)

    val op = Region.Op.REPLACE

    //создание региона
    val region = Region().apply {
        //присваиваем региону первый прямоугольник
        set(rect1)
        /*
            используем метод op чтобы добавлять к региону дополнительные прямоугольники, при этом
                указываем режим добавления:
                - Region.Op.UNION. Если при добавлении нового прямоугольника к региону
                    используется режим UNION, то итоговый регион будет являться объединением области
                    текущего региона и добавляемого прямоугольника.
                - Region.Op.XOR. Итоговая область региона: области обоих прямоугольников за
                    исключением их пересечения.
                - Region.Op.DIFFERENCE. Итоговая область региона: область первого прямоугольника
                    за исключением пересечения его со вторым.
                - Region.Op.REVERSE_DIFFERENCE. Итоговая область региона: область второго
                    прямоугольника за исключением пересечения его с первым.
                - Region.Op.INTERSECT. Итоговая область региона: пересечение обоих прямоугольников
                - Region.Op.REPLACE. Итоговая область региона: второй прямоугольник. Т.е.
                    содержимое региона заменилось вторым прямоугольником.
         */
        op(rect2, op)
    }

    //создание path из региона
    val path = region.boundaryPath

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //контуры прямоугольника
            p.style = Paint.Style.STROKE
            p.color = Color.BLACK
            drawRect(rect1, p)
            drawRect(rect2, p)

            //path
            p.style = Paint.Style.FILL
            p.color = Color.BLUE
            drawPath(path, p)
        } ?: return
    }
}
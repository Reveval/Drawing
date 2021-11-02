package ru.startandroid.develop.p1421drawingfigure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View

class DrawViewExm1(context: Context) : View(context) {
    // создаем объект Paint, которым будем рисовать фигуры
    private val p = Paint()
    /*
        создаем объект Rect, который нам понадобится для рисования прямоугольника.
            Создавать объекты крайне желательно за пределами метода onDraw, т.к. при частой
            прорисовке у вас постоянно будут создаваться новые объекты, а это является лишней
            нагрузкой на сборщик мусора и может замедлить работу приложения. Поэтому создаем
            мы объекты всего один раз, в конструкторе.
     */
    private val rect = Rect()

    //В методе onDraw мы сначала закрашиваем всю канву цветом.
    override fun onDraw(canvas: Canvas?) {
        //настраиваем кисть
        p.color = Color.RED
        //ставим толщину линии
        p.strokeWidth = 10F

        canvas?.apply {
            //заливка канвы цветом
            drawARGB(80, 102, 204, 255)
            //рисуем точку (50, 50)
            drawPoint(50F, 50F, p)
            //рисуем линию от (100, 100) до (500, 50)
            drawLine(100F, 100F, 500F, 50F, p)
            // рисуем круг с центром в (100,200), радиус = 50
            drawCircle(100F, 200F, 50F, p)
            //рисуем прямоугольник - левая верхняя точка (200,150), нижняя правая (400,200)
            drawRect(200F, 150F, 400F, 200F, p)
        }

        //настраиваем объект Rect - левая верхняя точка (250,300), нижняя правая (350,500)
        rect.set(250, 300, 350, 500)
        //рисуем прямоугольник из объекта rect
        canvas?.drawRect(rect, p)
    }
}
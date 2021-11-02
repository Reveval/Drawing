package ru.startandroid.develop.p1431drawingpath

import android.content.Context
import android.graphics.*
import android.view.View

//рисуем простые фигуры при помощи path
class DrawViewExm1(context: Context) : View(context) {
    val p = Paint()
    val rectF = RectF(350F, 100F, 400F, 150F)
    val path = Path()
    val path1 = Path()

    init {
        p.apply {
            strokeWidth = 3F
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        path.run {
            //очистка path
            reset()

            //угол
            moveTo(100F, 100F)
            lineTo(150F, 200F)
            lineTo(50F, 200F)

            //треугольник
            moveTo(250F, 100F)
            lineTo(300F, 200F)
            lineTo(200F, 200F)
            /*
                когда используем close начальная и конечная точки соединяются между собой
                    линией и фигура закрывается. Т.о. получаем треугольник
             */
            close()

            /*
                квадрат и круг. Здесь используем параметр направление. Он может принимать два
                    варианта CW(по часовой) и CCW(против часовой). Т.о. мы задаем
                    напрвление рисвания линий квдрата или фигуры
             */
            addRect(rectF, Path.Direction.CW)
            addCircle(450F, 150F, 25F, Path.Direction.CW)

            //рисование path
            p.color = Color.BLACK
            canvas.drawPath(path, p)
        }

        path1.run {
            //очистка path1
            reset()
            //две пересекающиеся линии
            moveTo(50F, 50F)
            lineTo(500F, 250F)
            moveTo(500F, 50F)
            lineTo(50F, 250F)

            //рисуем path1
            p.color = Color.GREEN
            canvas.drawPath(path1, p)
        }

        //добавление path1 к path
        path.addPath(path1)
        //смещение
        path.offset(500F, 100F)
        //рисование path
        p.color = Color.BLUE
        canvas.drawPath(path, p)
    }
}
package ru.startandroid.develop.p1421drawingfigure

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

//Посмотрим на другие методы рисования и будем менять некоторые используемые объекты в процессе.
class DrawViewExm2(context: Context) : View(context) {
    private val p = Paint()
    private val rectF = RectF(700F, 100F, 800F, 150F)
    private val points = arrayOf(100F, 50F, 150F, 100F, 150F, 200F, 50F, 200F, 50F, 100F)
        .toFloatArray()
    private val points1 = arrayOf(300F ,200F, 600F, 200F, 300F, 300F, 600F, 300F, 400F, 100F, 400F,
        400F, 500F, 100F, 500F, 400F).toFloatArray()

    override fun onDraw(canvas: Canvas?) {
        p.apply {
            color = Color.RED
            strokeWidth = 10F
        }

        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            //рисуем точки из массива points
            drawPoints(points, p)
            //рисуем линии по точкам из массива points1
            drawLines(points1, p)

            //перенастраиваем кисть на зеленый цвет
            p.color = Color.GREEN
            //рисуем закругленный прямоугольник по координатам из RectF с радиусами закругления 20
            drawRoundRect(rectF, 20F, 20F, p)
            /*
                смещаем координаты RectF на 150 вниз. Значения 0F и 150F добавляется к уже заданным
                    значениям исходного прямоугольника rectF
             */
            rectF.offset(0F, 150F)
            //рисуем овал внутри прямоугольника RectF - он займет все пространство прямоугольника
            drawOval(rectF, p)

            /*
                смещаем rectf в (900,100) (левая верхняя точка). Метод offsetTo не добавляет
                    координаты к имеющимся, а устанавливает новую верхнюю левую точку
                    прямоугольника. Последний смещается к ней, сохраняя при этом свои размеры.
             */
            rectF.offsetTo(900F, 100F)
            /*
                увеличиваем rectf по вертикали на 25 вниз и вверх - изменения происходят с обеих
                сторон. Чтобы уменьшить прямоугольник, нужно наоборот передать положительное
                число
             */
            rectF.inset(0F, -25F)
            /*
                рисуем дугу внутри прямоугольника rectf с началом в 90, длиной 270 и соединением
                    крайних точек через центр (параметр true)
             */
            drawArc(rectF, 90F, 270F, true, p)

            //смещаем коорднаты rectf на 150 вниз
            rectF.offset(0F, 150F)
            /*
                рисуем дугу внутри прямоугольника rectf с началом в 90, длиной 270 и соединением
                    крайних точек напрямую (параметр false)
             */
            drawArc(rectF, 90F, 270F, false, p)

            //перенастраиваем кисть на толщину линии = 3
            p.strokeWidth = 3F
            //рисуем линию (150,450) - (150,600)
            drawLine(150F, 450F, 150F, 600F, p)

            //перенастраиваем кисть на синий цвет
            p.color = Color.BLUE
            // настраиваем размер текста = 30
            p.textSize = 30F
            // рисуем текст в точке (150,500)
            drawText("text left", 150F, 500F, p)

            //настраиваем выравнивание текста на центр
            p.textAlign = Paint.Align.CENTER
            //рисуем текст в точке (150,525)
            drawText("text center", 150F, 525F, p)

            /*
                настраиваем выравнивание текста на левое (RIGHT в этом случае означает "точка
                    будет справа от текста", соответственно текст будет выровнен слева и наоборот
             */
            p.textAlign = Paint.Align.RIGHT
            //рисуем текст в точке (150,550)
            drawText("text right", 150F, 550F, p)
        }
    }
}
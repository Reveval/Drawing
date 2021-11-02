package ru.startandroid.develop.p1531colorfilter

import android.content.Context
import android.graphics.*
import android.view.View

//меняем цвет кисти с помощью ColorFilter
class DrawViewExm1(context: Context) : View(context) {
    //cmData – массив float, сюда пишем значения для матрицы.
    val cmData = arrayOf(
        1F, 0F, 0F, 0F, 0F,
        0F, 1F, 0F, 0F, 0F,
        0F, 0F, 1F, 0F, 0F,
        0F, 0F, 0F, 0.3F, 0F
    ).toFloatArray()

    val rect = Rect(0, 0, 200, 200)

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL_AND_STROKE
    }

    val icon = BitmapFactory.decodeResource(context.resources,
        R.drawable.icon)

    //Переменная colorMatrix – это и есть матрица – ColorMatrix. Ей мы даем массив cmData.
    val colorMatrix = ColorMatrix(cmData)
    /*
        Эту матрицу мы указываем при создании фильтра filter. Теперь у фильтра есть
            матрица и он знает какие преобразования цвета ему необходимо будет произвести.
     */
    val filter = ColorMatrixColorFilter(colorMatrix)

    /*
        В onDraw рисуем объекты метолом drawObjects, затем для кисти paint применяем
            фильтр методом setColorFilter и снова выводим объекты. Т.к. при рисовании
            объектов используется кисть paint, то применение фильтра к кисти повлияет на
            цвета рисуемых фигур.
     */
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            translate(100F, 100F)
            drawObjects(this)

            paint.colorFilter = filter
            translate(0F, 300F)
            drawObjects(this)
        } ?: return
    }

    /*
        В методе drawObjects рисуем 4 квадрата – красный, зеленый, синий, белый, и
            выводим андроид-иконку. На этих объектах мы будет тестировать изменения цвета.
            Используем кисть paint.
     */
    private fun drawObjects(canvas: Canvas) {
        canvas.apply {
            save()

            paint.color = Color.RED
            drawRect(rect, paint)

            paint.color = Color.GREEN
            translate(220F, 0F)
            drawRect(rect, paint)

            paint.color = Color.BLUE
            translate(220F, 0F)
            drawRect(rect, paint)

            paint.color = Color.WHITE
            translate(220F, 0F)
            drawRect(rect, paint)

            translate(220F, 0F)
            drawBitmap(icon, null, rect, paint)
            restore()
        }
    }
}
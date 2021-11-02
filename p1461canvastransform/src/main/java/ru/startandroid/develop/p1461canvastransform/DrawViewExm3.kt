package ru.startandroid.develop.p1461canvastransform

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.view.View

/*
    Сохранение методом save не затирает предыдущее сохраненное состояние. Т.е. вы можете
        несколько раз вызывать save и все эти состояния будут хранится в некоем стеке. А методом
        restore состояния из этого стека вытаскивать.
 */
class DrawViewExm3(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val rectF1 = RectF(50F, 50F, 100F, 100F)
    val rectF2 = RectF(50F, 150F, 100F, 200F)

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            save()

            //зеленый квадрат
            p.color = Color.GREEN
            drawRect(rectF1, p)

            //преобразование канвы и рисование зеленых квадратов
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)

            //сохраняем настройки матрицы канвы
            save()

            //преобразования канвы и рисование желтых квадратов
            p.color = Color.YELLOW
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)

            //сохраняем настройки матрицы канвы
            save()

            //преобразование канвы и рисование красных квадратов
            p.color = Color.RED
            translate(100F, 0F)
            drawRect(rectF1, p)
            translate(100F, 0F)
            drawRect(rectF1, p)

            //возврат канвы к предыдущему сохранению
            restore()

            //синий квадрат
            p.color = Color.BLUE
            drawRect(rectF2, p)

            //возврат канвы к предыдущему сохранению
            restore()

            //черный квадрат
            p.color = Color.BLACK
            drawRect(rectF2, p)

            //возврат канвы в изначальное состояние
            restore()

            //пурпурный квадрат
            p.color = Color.MAGENTA
            drawRect(rectF2, p)
        } ?: return
    }
}
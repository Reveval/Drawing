package ru.startandroid.develop.p1511patheffect

import android.content.Context
import android.graphics.*
import android.view.View

/*
    DiscretePathEffect
        DiscretePathEffect позволяет получить ломанную линию из прямой. Полученная ломанная
        линия будет состоять из фрагментов, а мы можем повлиять на длину этих фрагментов
        (первый параметр конструктора) и степень излома (второй параметр).
 */
class DrawViewExm3(context: Context) : View(context) {
    val path = Path().apply {
        rLineTo(100F, 300F)
        rLineTo(100F, -100F)
        rLineTo(100F, 300F)
    }

    //создаем черную кисть без эффектов
    val mainPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 7F
    }

    /*
        Для зеленой линии мы настраиваем длину выводимого участка = 30, длину пустоты = 10. Мы
            помещаем эти значение в массив и передаем в DashPathEffect-конструктор первым
            параметром. Вторым параметром идет отступ, его не используем.
     */
    val greenPaintWithDashPath = Paint(mainPaint).apply {
        color = Color.GREEN
        pathEffect = DashPathEffect(arrayOf(30F, 10F).toFloatArray(), 0F)
    }

    /*
        Для синей линии мы задаем чуть более сложную последовательность: 50 выводить, 10 пусто, 5,
        выводить, 10 пусто. Т.е. принцип наверно уже понятен. Система будет поочередно
        использовать значения из массива для определения длины рисуемого куска линии и длины
        следующей за ним пустоты. Отступ используем в 25.
     */
    val bluePaintWithDashPath = Paint(mainPaint).apply {
        color = Color.BLUE
        pathEffect = DashPathEffect(arrayOf(50F, 10F, 5F, 10F).toFloatArray(), 25F)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            translate(100F, 100F)
            drawPath(path, mainPaint)

            translate(250F, 0F)
            drawPath(path, greenPaintWithDashPath)

            translate(250F, 0F)
            drawPath(path, bluePaintWithDashPath)
        } ?: return
    }
}
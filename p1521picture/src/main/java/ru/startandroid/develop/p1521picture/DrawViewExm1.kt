package ru.startandroid.develop.p1521picture

import android.content.Context
import android.graphics.*
import android.view.View

/*
    Мы можем записать операции рисования на канве в некий шаблон, а затем многократно
        воспроизводить его.  Для этого используется объект Picture.
 */
class DrawViewExm1(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val picture = Picture()
    val path = Path()

    init {
        /*
            Сначала создаем объект Picture, затем методом beginRecording начинаем запись.
                Этот метод возвращает нам канву, на ней мы и будем выполнять все операции,
                которые будут записаны. А на вход методу beginRecording необходимо
                передать ширину и высоту изображения, которое вы собираетесь записывать.
                Т.е. наш шаблон будет размером 300х300.
         */
        val canvas = picture.beginRecording(300, 300)

        //Записываем рисование круга, квадрата и треугольника
        paint.color = Color.GREEN
        canvas.drawCircle(150F, 100F, 80F, paint)

        paint.color = Color.BLUE
        canvas.drawRect(20F, 70F, 150F, 200F, paint)

        paint.color = Color.RED
        path.apply {
            moveTo(170F, 80F)
            lineTo(240F, 210F)
            lineTo(100F, 210F)
            close()
        }
        canvas.drawPath(path, paint)

        //методом endRecording, завершаем запись
        picture.endRecording()
    }

    //В onDraw методом drawPicture выводим на нашу канву все то, что записали в picture.
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawPicture(picture)
        } ?: return
    }
}
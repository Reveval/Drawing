package ru.startandroid.develop.p1521picture

import android.content.Context
import android.graphics.*
import android.view.View

//У drawPicture есть, также, реализация, где мы можем менять размер выводимого изображения.
class DrawViewExm2(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    val picture = Picture()
    val path = Path()
    val square = Rect(0, 0, 100, 100)
    val rect = Rect(0, 0, 500, 200)

    init {
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

            translate(300F, 20F)
            drawPicture(picture, square)

            translate(0F, 300F)
            drawPicture(picture, rect)
        } ?: return
    }
}
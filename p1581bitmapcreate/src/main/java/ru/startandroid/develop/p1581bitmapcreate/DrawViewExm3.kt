package ru.startandroid.develop.p1581bitmapcreate

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import java.util.*

//создание чистого Bitmap
class DrawViewExm3(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //создаем чистый Bitmap размером 100х100
    val bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.RGB_565).apply {
        //Методом setPixel ставим в нем три пиксела (20,20), (70,50) и (30,80) в красный цвет.
        setPixel(20, 20, Color.RED)
        setPixel(70, 50, Color.RED)
        setPixel(30, 80, Color.RED)
    }

    val colors = IntArray(10*15)

    init {
        Arrays.fill(colors, 0, 10*15, Color.GREEN)
        /*
            Методом setPixels можем менять пикселы не по одному, а массивом. Этот метод
                аналогичен методам создания Bitmap из массива цветов. Первые три параметра – это
                массив, offset и stride. Затем идут координаты точки, с которой начнем закраску
                (40,40), затем размер закрашиваемой области (10,15).
         */
        bitmap.setPixels(colors, 0, 10, 40, 40, 10, 15)
    }

    val canvas = Canvas(bitmap).apply {
        val paint = Paint()
        paint.color = Color.BLUE
        drawCircle(80F, 80F, 10F, paint)
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawBitmap(bitmap, 50F, 50F, paint)
        } ?: return
    }
}
package ru.startandroid.develop.p1581bitmapcreate

import android.content.Context
import android.graphics.*
import android.view.View

class DrawViewExm1(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    //создаем bitmapSource из стандартной иконки ic_launcher.
    val bitmapSource = BitmapFactory.decodeResource(resources, R.drawable.icon)

    //Настраиваем матрицу на поворот и изменение размера.
    val mMatrix = Matrix().apply {
        postScale(10F, 15F)
        postRotate(45F)
    }

    /*
        создаем bitmap на основе bitmapSource. Берем кусок начиная с точки (0,0) размерами в
            половину ширины и половину высоты. Т.е. получается левая верхняя четверть
            изображения. Система возьмет эту часть, применит к ней матрицу и фильтр, и выдаст
            нам, как новый Bitmap.
     */
    val bitmap = Bitmap.createBitmap(bitmapSource, 0, 0, bitmapSource.width / 2,
        bitmapSource.height / 2, mMatrix, true)

    //В onDraw отображаем полученный Bitmap.
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawBitmap(bitmap, 0F, 0F, paint)
        }
    }
}
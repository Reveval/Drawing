package ru.startandroid.develop.p1651shader

import android.content.Context
import android.graphics.*
import android.view.View

/*
    BitmapShader
        Для создания этого шейдера необходимо передать ему Bitmap и указать вертикальный и
        горизонтальный TileMode. Читаем icon в Bitmap, создаем шейдер и передаем этот шейдер в
        метод кисти setShader. И используя эту кисть рисуем квадрат и круг.
 */
class DrawViewExm1(context: Context) : View(context) {
    val mShader = createShader()
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        shader = mShader
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawRect(100F, 100F, 400F, 300F, paint)
            drawCircle(300F, 400F, 100F, paint)
        } ?: return
    }

    //создадим шейдер и применим к нему некоторые преобразования, используя матрицу
    private fun createShader() : Shader {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon)
        val shader =  BitmapShader(bitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT)
        val matrix = Matrix().apply {
            postScale(2F, 1.5F)
            postRotate(45F)
        }
        shader.setLocalMatrix(matrix)
        return shader
    }
}
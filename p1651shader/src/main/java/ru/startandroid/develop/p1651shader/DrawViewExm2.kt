package ru.startandroid.develop.p1651shader

import android.content.Context
import android.graphics.*
import android.view.View

/*
    LinearGradient
        Этот шейдер позволяет нам получить градиент. У его класса есть два конструктора. Оба
        просят от нас указать им TileMode и координаты линии, которая будет задавать одновременно
        направление, начало и размер градиента.
        Разница заключается в способе указания цветов для градиента. Один конструктор просит от
        нас указать ему два цвета. По ним он и нарисует градиент.
 */
class DrawViewExm2(context: Context) : View(context) {
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
        return LinearGradient(0F, 0F, 100F, 20F, Color.RED, Color.GREEN,
            Shader.TileMode.MIRROR)
    }
}
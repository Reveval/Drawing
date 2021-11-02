package ru.startandroid.develop.p1661customdrawable

import android.graphics.*
import android.graphics.drawable.Drawable

open class HexagonDrawable : Drawable() {
    protected val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    /*
        В методе draw просто выводим path (который будет сформирован в другом методе) на канву,
            используя кисть paint.
     */
    override fun draw(canvas: Canvas) {
        canvas.drawPath(path, paint)
    }

    //Методы setAlpha и setColorFilter просто переадресуем кисти paint.
    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paint.colorFilter = colorFilter
    }

    /*
        В методе getOpacity возвращаем TRANSLUCENT, т.к. у нас будет непрозрачный шестиугольник,
            а оставшееся пространство Drawable будет прозрачным. Хелп, кстати, рекомендует
            использовать именно TRANSLUCENT, если точно не знаете, что указать.
     */
    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    /*
        onBoundsChange вызывается когда меняется размер Drawable. А т.к. нам нужно нарисовать
            6-тиугольник размером с Drawable, мы должны знать его размер. Здесь мы получаем ширину
            и высоту Drawable и используем их для создания path-фигуры 6-тиугольника.
     */
    override fun onBoundsChange(bounds: Rect?) {
        if (bounds == null) return
        super.onBoundsChange(bounds)
        val width = bounds.width().toFloat()
        val height = bounds.height().toFloat()

        path.apply {
            reset()
            moveTo(0F, height / 2)
            lineTo(width / 4, 0F)
            lineTo(width * 3 / 4, 0F)
            lineTo(width, height / 2)
            lineTo(width * 3 / 4, height)
            lineTo(width / 4, height)
            close()
        }
    }
}
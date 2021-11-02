package ru.startandroid.develop.p1581bitmapcreate

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View
import java.util.*


//создание bitmap из готового массива цветов
class DrawViewExm2(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /*
        создаем массив цветов количеством 300 * 300 = 90 000 элементов. Число выбрано такое, т.к.
            картинку мы будем создавать с шириной и высотой 300. Соответственно и цветов (читай
            пикселов) нам надо будет 300 * 300.
     */
    val colors = IntArray(300 * 300)

    init {
        /*
            Заполняем первую треть массива полупрозрачным красным цветом, вторую – зеленым,
                третью – синим.
         */
        Arrays.fill(colors, 0, 300*100, Color.argb(85, 255,
            0, 0))
        Arrays.fill(colors, 300*100, 300*200, Color.GREEN)
        Arrays.fill(colors, 300*200, 300*300, Color.BLUE)
    }

    /*
        Создаем bitmap, используя массив цветов и указав: ширину 300, высоту 300,
            конфигурацию - RGB_565.
     */
    val bitmap = Bitmap.createBitmap(colors, 300, 300, Bitmap.Config.RGB_565)
    //Аналогично создаем bitmapAlpha, но конфигурацию укажем ARGB_8888.
    val bitmapAlpha = Bitmap.createBitmap(colors, 300, 300, Bitmap.Config.ARGB_8888)

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawBitmap(bitmap, 50F, 50F, paint)
            drawBitmap(bitmapAlpha, 550F, 50F, paint)
        } ?: return
    }

}
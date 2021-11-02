package ru.startandroid.develop.p1491canvastext

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Typeface
import android.view.View

//Рассмотрим возможность использования типов и стилей шрифтов.
class DrawViewExm3(context: Context) : View(context) {
    val fontSize = 60F
    val text = "Test width text"
    val mY = 80F

    val p = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = fontSize
        style = Paint.Style.FILL
    }

    /*
        В onDraw мы выводим один и тот же текст, используя различные типы и стили
            шрифта. Для этого используем метод setTypeface, который требует на вход
            Typeface. Создать Typeface можно методом create, который требует на вход
            тип и стиль. Сначала используем шрифт по умолчанию, затем строим различные
            комбинации из типов:

            - MONOSPACE – моноширинный, т.е. ширина всех символов одинакова
            - SERIF – шрифт с засечками
            - DEFAULT - шрифт по умолчанию

            и стилей:

            - NORMAL – обычный
            - BOLD – жирный
            - BOLD_ITALIC – жирный курсивный
            - ITALIC - курсивный
     */
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            //обычный текст
            translate(50F, mY)
            drawText(text, 0F, 0F, p)

            //моноширинный
            translate(0F, mY)
            p.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.NORMAL)
            drawText(text, 0F, 0F, p)

            //с засечками
            translate(0F, mY)
            p.typeface = Typeface.create(Typeface.SERIF, Typeface.NORMAL)
            drawText(text, 0F, 0F, p)

            //обычный жирный
            translate(0F, mY)
            p.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
            drawText(text, 0F, 0F, p)

            //обычный жирный курсивный
            translate(0F, mY)
            p.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD_ITALIC)
            drawText(text, 0F, 0F, p)

            //обычный курсивный
            translate(0F, mY)
            p.typeface = Typeface.create(Typeface.DEFAULT, Typeface.ITALIC)
            drawText(text, 0F, 0F, p)
        } ?: return
    }
}
package ru.startandroid.develop.p1541porterduff

import android.content.Context
import android.graphics.*
import android.view.View

/*
    PorterDuff-режимы позволяют нам получать различные результаты при наложении одного
        изображения на другое. Т.е. берутся значения цвета и прозрачности обоих изображений и
        по определенному алгоритму рассчитываются итоговые значения.
 */
class DrawViewExm1(context: Context) : View(context) {
    //PorterDuff режим
    private val mode = PorterDuff.Mode.DST_OUT

    private val colorSrc = Color.argb(85, 255, 255, 0)
    private val colorDst = Color.argb(170, 0, 0, 255)

    private val paintSrc: Paint
    private val paintDst: Paint
    private val paintBorder: Paint

    private val pathSrc: Path
    private val pathDst: Path

    private val bitmapSrc: Bitmap
    private val bitmapDst: Bitmap

    init {
        // необходимо для корректной работы
        setLayerType(LAYER_TYPE_SOFTWARE, null)

        //DST фигура
        pathDst = Path().apply {
            moveTo(0F, 0F)
            lineTo(500F, 0F)
            lineTo(500F, 500F)
            close()
        }

        //создание DST bitmap
        bitmapDst = createBitmap(pathDst, colorDst)

        //кисть для вывода DST bitmap
        paintDst = Paint()

        //SRC фигура
        pathSrc = Path().apply {
            moveTo(0F, 0F)
            lineTo(500F, 0F)
            lineTo(0F, 500F)
            close()
        }

        //создание SRC bitmap
        bitmapSrc = createBitmap(pathSrc, colorSrc)

        //кисть для вывода SRC bitmap
        paintSrc = Paint().apply {
            xfermode = PorterDuffXfermode(mode)
        }

        //кисть для рамки
        paintBorder = Paint().apply {
            style = Paint.Style.STROKE
            strokeWidth = 3F
            color = Color.BLACK
        }
    }

    /*
        В методе createBitmap создаем Bitmap размерами 500х500, создаем для него персональную
            канву и на ней рисуем полученный path указанным цветом.
     */
    private fun createBitmap(path: Path, color: Int) : Bitmap {
        //создание bitmap и канвы для него
        val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.ARGB_8888)
        val bitmapCanvas = Canvas(bitmap)

        //создание кисти нужного цвета
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.FILL_AND_STROKE
            setColor(color)
        }

        //рисование фигуры на канве bitmap
        bitmapCanvas.drawPath(path, paint)
        return bitmap
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            translate(390F, 80F)

            //DST bitmap
            drawBitmap(bitmapDst, 0F, 0F, paintDst)

            //SRC bitmap
            drawBitmap(bitmapSrc, 0F, 0F, paintSrc)

            //рамка
            drawRect(0F, 0F, 500F, 500F, paintBorder)
        } ?: return
    }
}
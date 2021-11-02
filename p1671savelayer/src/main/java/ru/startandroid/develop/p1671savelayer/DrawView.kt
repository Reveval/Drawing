package ru.startandroid.develop.p1671savelayer

import android.content.Context
import android.graphics.*
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi

class DrawView(context: Context) : View(context) {
    val rect = Rect(0, 40, 750, 370)
    val rectF = RectF(rect)
    val shaderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        shader = createShader()
        xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
    }
    val blackPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        alpha = 100
    }
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.effect)

    init {
        bitmap = Bitmap.createScaledBitmap(bitmap, rect.width(), rect.height(), true)
    }

    //В методе onDraw нарисуем на экране овал, используя созданный шейдер.
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawBitmap(bitmap, 0F, 0F, paint)
            saveLayer(rectF, paint)
            drawRect(rect, blackPaint)
            drawOval(rectF, shaderPaint)
            restore()
        } ?: return
    }

    /*
        Для создания шейдера в createShader используем градиент-шейдер. Он черный (ff000000) в
            центре и будет становиться прозрачным (00000000) к краям. Обратите внимание, что мы
            создали его в точке (0,0) и радиусом он всего 1. Далее мы применяем к нему матрицу,
            чтобы поместить его в нужную точку и придать ему необходимые размеры.
     */
    private fun createShader() : Shader {
        val colors = arrayOf(0xff000000.toInt(), 0xff000000.toInt(), 0).toIntArray()
        val anchors = arrayOf(0F, 0.5F, 1F).toFloatArray()
        val shader = RadialGradient(0F, 0F, 1F, colors, anchors,
            Shader.TileMode.CLAMP)
        Matrix().apply {
            postTranslate(rect.centerX().toFloat(), rect.centerY().toFloat())
            postScale((rect.width() / 2).toFloat(), (rect.height() / 2).toFloat(),
                rect.centerX().toFloat(), rect.centerY().toFloat()
            )
            shader.setLocalMatrix(this)
        }
        return shader
    }
}
package ru.startandroid.develop.p1551porterduffcolorfilter

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawView(this))
    }

    class DrawView(context: Context) : View(context) {
        private val size = 200

        private val mode = PorterDuff.Mode.DARKEN
        private val colorSrc = arrayOf(Color.WHITE, Color.LTGRAY, Color.GRAY, Color.DKGRAY,
            Color.BLACK)

        private var bitmap: Bitmap
        private val paints: Array<Paint?>
        private val paintBorder: Paint

        init {
            //необходимо для корректной работы
            setLayerType(LAYER_TYPE_SOFTWARE, null)

            //создаем bitmap картинку необходимого размера
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon)
            bitmap = Bitmap.createScaledBitmap(bitmap, size, size, true)

            //создание массива кистей paints
            paints = arrayOfNulls(colorSrc.size)
            for (i in colorSrc.indices) {
                val paint = Paint(Paint.ANTI_ALIAS_FLAG)
                /*
                    для каждой кисти свой PorterDuffColorFilter с цветом из массива colorSrc
                 */
                paint.colorFilter = PorterDuffColorFilter(colorSrc[i], mode)
                paints[i] = paint
            }

            //кисть для рамок
            paintBorder = Paint().apply {
                style = Paint.Style.STROKE
                strokeWidth = 3F
                color = Color.BLACK
            }
        }

        override fun onDraw(canvas: Canvas?) {
            canvas?.apply {
                translate(0F, 200F)
                val delta = ((width - size * paints.size) / (paints.size + 1)).toFloat()

                //рисование bitmap
                for (i in paints.indices) {
                    translate(delta, 0F)
                    //используем кисти из массива paints
                    drawBitmap(bitmap, 0F, 0F, paints[i])
                    drawRect(0F, 0F, size.toFloat(), size.toFloat(), paintBorder)
                    translate(size.toFloat(), 0F)
                }
            } ?: return
        }
    }
}
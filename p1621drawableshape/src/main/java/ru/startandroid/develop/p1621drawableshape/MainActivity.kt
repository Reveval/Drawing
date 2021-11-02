package ru.startandroid.develop.p1621drawableshape

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

/*
    Drawable – это абстрактный контейнер для графического объекта. Его главное абстрактное умение
        – он может нарисовать свое содержимое на предоставленной ему канве. А вот что конкретно
        он нарисует, зависит уже от реализации. Это может быть, например, тот же Bitmap, если мы
        используем BitmapDrawable объект. Или это может быть простая геометрическая фигура, если
        мы используем ShapeDrawable.
 */
class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        setDrawable()
    }

    /*private fun setDrawable() {
        imageView.setImageResource(R.drawable.shape)
    }*/

    //Програмное создание GradientDrawable
    private fun setDrawable() {
        val drawable = GradientDrawable(GradientDrawable.Orientation.BL_TR, arrayOf(Color.RED,
            Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA).toIntArray()).apply {
                shape = GradientDrawable.RECTANGLE
                gradientType = GradientDrawable.LINEAR_GRADIENT
                cornerRadius = 40F
                setStroke(10, Color.BLACK, 20F, 5F)
        }
        imageView.setImageDrawable(drawable)
    }

}
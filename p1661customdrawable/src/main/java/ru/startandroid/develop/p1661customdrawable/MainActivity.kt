package ru.startandroid.develop.p1661customdrawable

import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

//создаем собственный Drawable
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageView = findViewById<ImageView>(R.id.image_view)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.picture)
        val drawable = BitmapHexagonDrawable(bitmap)
        imageView.background = drawable

    }
}
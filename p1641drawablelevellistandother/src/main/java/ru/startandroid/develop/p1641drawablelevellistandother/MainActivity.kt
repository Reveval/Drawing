package ru.startandroid.develop.p1641drawablelevellistandother

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView
    lateinit var animDrawable: Drawable
    lateinit var buttonStart: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.image_view_main)
        buttonStart = findViewById(R.id.button_start)

        animDrawable = imageView.background

        buttonStart.setOnClickListener {
            (animDrawable as AnimationDrawable).start()
        }
    }

    fun onClickLevel(view: View) {
        view.setOnClickListener {
            when(view.id) {
                R.id.button_level0 -> animDrawable.level = 0
                R.id.button_level1 -> animDrawable.level = 1
                R.id.button_level2 -> animDrawable.level = 2
            }
        }
    }
}
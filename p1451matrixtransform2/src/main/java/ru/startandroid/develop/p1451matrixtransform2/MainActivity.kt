package ru.startandroid.develop.p1451matrixtransform2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//Исследуем возможности методов setRectToRect и setPolyToPoly
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawViewExm5(this))
    }
}
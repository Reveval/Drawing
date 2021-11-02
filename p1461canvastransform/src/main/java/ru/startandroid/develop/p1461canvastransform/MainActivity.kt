package ru.startandroid.develop.p1461canvastransform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

//используем матрицу канвы для преобразований
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawViewExm4(this))
    }
}
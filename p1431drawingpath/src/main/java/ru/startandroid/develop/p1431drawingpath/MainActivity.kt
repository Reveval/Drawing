package ru.startandroid.develop.p1431drawingpath

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
    Исследуем возможность создавать сложные фигуры с помощью объекта Path. Этот объект позволяет
        нам создать составную фигуру, состоящую из линий, кривых и простых фигур.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawViewExm3(this))
    }
}
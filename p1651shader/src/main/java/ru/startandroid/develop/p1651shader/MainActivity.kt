package ru.startandroid.develop.p1651shader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

/*
    Используя подклассы класса Shader мы получаем возможность "рисовать рисунком". Для этого
        необходимо передать объект Shader в метод кисти setShader и кисть будет использовать
        рисунок шейдера для рисования объектов. Рассмотрим существующих наследников класса Shader.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawViewExm2(this))
    }
}
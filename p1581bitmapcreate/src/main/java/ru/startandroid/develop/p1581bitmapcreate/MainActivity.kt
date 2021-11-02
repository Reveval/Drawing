package ru.startandroid.develop.p1581bitmapcreate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
    Рассмотрим способы создания Bitmap. Для этого есть несколько статических методов createBitmap.
        Все эти методы создания Bitmap можно разделить на три группы:
            1) создание на основе другого Bitmap
            2) создание из массива цветов
            3) создание пустого Bitmap
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawViewExm3(this))
    }


}
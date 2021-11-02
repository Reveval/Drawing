package ru.startandroid.develop.p1591bitmapoptions

import android.content.Context
import android.graphics.*
import android.os.Environment
import android.view.View
import java.io.File
import java.io.FileOutputStream

class DrawViewExm4(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 40F
    }
    val bitmap = Bitmap.createBitmap(500, 500, Bitmap.Config.RGB_565)

    /*
        В bmpIcon читаем стандартную иконку, затем меняем размер на 500х500. Создаем новый
            bitmap, заполняем его белым, рисуем в нем bmpIcon и пишем текст.
     */
    var bmpIcon = BitmapFactory.decodeResource(context.resources, R.drawable.icon)

    init {
        bmpIcon = Bitmap.createScaledBitmap(bmpIcon, 500, 500, true)

        Canvas(bitmap).apply {
            drawColor(Color.WHITE)
            drawBitmap(bmpIcon, 0F, 0F, paint)
            drawText("Saved bitmap", 100F, 50F, paint)
        }

        /*
            Далее создаем объект File, который указывает на файл savedBitmap.png в стандартной
                папке Pictures. Для этого файла создаем поток FileOutputStream, который передаем
                в метод compress. Также в методе указываем формат JPEG и качество = 100.
         */
        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
            "savedBitmap.png")

        try {
            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            } finally {
                fos?.close()
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawBitmap(bitmap, 100F, 100F, paint)
        } ?: return
    }
}
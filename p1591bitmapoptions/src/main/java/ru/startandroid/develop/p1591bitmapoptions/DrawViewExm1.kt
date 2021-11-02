package ru.startandroid.develop.p1591bitmapoptions

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.View

class DrawViewExm1(context: Context) : View(context) {

    init {
        val options = BitmapFactory.Options()
        /*
            inJustDecodeBounds
                Если включить (true) этот параметр, то система не будет создавать Bitmap, а только
                    вернет информацию о изображение в следующих полях:
                     - outWidth – ширина
                     - outHeight – высота
                     - outMimeType – mimetype
         */
        options.inJustDecodeBounds = true
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.icon, options)
        Log.d("myLogs", "bitmap = $bitmap, width = ${options.outWidth}, height = " +
                "${options.outHeight}, mimetype = ${options.outMimeType}")
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawARGB(80, 102, 204, 255)
    }
}
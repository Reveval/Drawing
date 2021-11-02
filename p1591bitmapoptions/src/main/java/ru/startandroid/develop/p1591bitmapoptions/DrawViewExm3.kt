package ru.startandroid.develop.p1591bitmapoptions

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.View

class DrawViewExm3(context: Context) : View(context) {
    init {
        val tempBitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888)
        val options = BitmapFactory.Options()
        /*
            inBitmap
                Если передать в этот параметр Bitmap-объект, то он и будет использован для
                получения результата вместо создания нового Bitmap-объекта.
         */
        options.inBitmap = tempBitmap
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.icon, options)
        Log.d("myLogs", "bitmap = $bitmap (${bitmap.width},${bitmap.height}), " +
                "tempBitmap = $tempBitmap")
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawARGB(80, 102, 204, 255)
    }
}
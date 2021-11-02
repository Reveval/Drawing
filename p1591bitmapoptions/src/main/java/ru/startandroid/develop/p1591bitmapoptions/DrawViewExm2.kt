package ru.startandroid.develop.p1591bitmapoptions

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import android.view.View

class DrawViewExm2(context: Context) : View(context) {
    init {
        val options = BitmapFactory.Options()
        /*
            inSampleSize
                Позволяет указать коэффициент уменьшения размера изображения при чтении. Он должен
                быть кратным 2. Если зададите другое число, то оно будет изменено на ближайшее
                число меньшее вашего и кратное 2.
         */
        options.inSampleSize = 2
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.icon, options)
        Log.d("myLogs", "width = ${bitmap.width}, height = ${bitmap.height}")
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawARGB(80, 102, 204, 255)
    }
}
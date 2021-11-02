package ru.startandroid.develop.p1661customdrawable

import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Rect
import android.graphics.Shader

/*
    В конструктор передаем Bitmap, а в onBoundsChange берем размеры Drawable, создаем Bitmap
        этого же размера, создаем на его основе шейдер и передаем его в кисть.
 */
class BitmapHexagonDrawable(bitmap: Bitmap) : HexagonDrawable() {
    val originBitmap = bitmap

    override fun onBoundsChange(bounds: Rect?) {
        if (bounds == null) return
        super.onBoundsChange(bounds)
        val bitmap = Bitmap.createScaledBitmap(originBitmap, bounds.width(), bounds.height(),
            true)
        val shader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.shader = shader
    }
}
package ru.startandroid.develop.p1441matrixtransform

import android.content.Context
import android.graphics.*
import android.view.View

//рассмотрим порядок операций при множественных трансформациях матрицы
class DrawViewExm4(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path()
    val pathDst = Path()
    val mMatrix = Matrix()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)
        p.color = Color.BLACK
        canvas.drawCircle(400F, 200F, 10F, p)

        //прямоугольник
        path.reset()
        path.addRect(300F, 100F, 500F, 300F, Path.Direction.CW)
        canvas.drawPath(path, p)

        mMatrix.run {
            //перемещение после поворота
            reset()
            setRotate(45F, 400F, 200F)
            postTranslate(500F, 0F)
            path.transform(this, pathDst)
            p.color = Color.GREEN
            canvas.drawPath(pathDst, p)

            //перемещение до поворота
            reset()
            setRotate(45F, 400F, 200F)
            preTranslate(500F, 0F)
            path.transform(this, pathDst)
            p.color = Color.RED
            canvas.drawPath(pathDst, p)
        }
    }
}
package ru.startandroid.develop.p1441matrixtransform

import android.content.Context
import android.graphics.*
import android.view.View

//наклон фигуры при помощи matrix
class DrawViewExm5(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path().apply {
        addRect(100F, 100F, 200F, 200F, Path.Direction.CW)
    }

    val pathDst = Path()
    val mMatrix = Matrix()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)
        p.color = Color.BLACK
        canvas.drawPath(path, p)
        p.color = Color.GREEN

        mMatrix.run {
            /*
                перемещение на 200 вправо и наклон по вертикали на 0.5 (точка наклона - слева)
            */
            reset()
            setTranslate(200F, 0F)
            postSkew(0.0F, 0.5F, 300F, 100F)
            path.transform(this, pathDst)
            canvas.drawPath(pathDst, p)
            canvas.drawCircle(300F, 100F, 5F, p)

            /*
                перемещение на 400 вправо и наклон по вертикали на 0.5 (точка наклона - справа)
            */
            reset()
            setTranslate(400F, 0F)
            postSkew(0.0F, 0.5F, 600F, 100F)
            path.transform(this, pathDst)
            canvas.drawPath(pathDst, p)
            canvas.drawCircle(600F, 100F, 5F, p)

            p.color = Color.BLUE

            /*
                перемещение на 150 вниз и наклон по горизонтали на 0.5 (точка наклона - сверху)
            */
            reset()
            setTranslate(0F, 150F)
            postSkew(0.5F, 0.0F, 100F, 250F)
            path.transform(this, pathDst)
            canvas.drawPath(pathDst, p)
            canvas.drawCircle(100F, 250F, 5F, p)

            /*
                перемещение на 300 вниз и наклон по горизонтали на 0.5 (точка наклона - снизу)
            */
            reset()
            setTranslate(0F, 300F)
            postSkew(0.5F, 0.0F, 100F, 500F)
            path.transform(this, pathDst)
            canvas.drawPath(pathDst, p)
            canvas.drawCircle(100F, 500F, 5F, p)
        }
    }

}
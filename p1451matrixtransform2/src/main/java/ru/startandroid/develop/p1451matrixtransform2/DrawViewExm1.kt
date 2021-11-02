package ru.startandroid.develop.p1451matrixtransform2

import android.content.Context
import android.graphics.*
import android.view.View

/*
setRectToRect
    Этот метод берет два прямоугольника и определяет какие преобразования необходимо выполнить над
        первым прямоугольником, чтобы он полностью поместился во втором. Эти преобразования
        записываются в матрицу и мы можем ее использовать.
 */
class DrawViewExm1(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val rectFDst = RectF()
    val rectFBounds = RectF()

    val path = Path().apply {
        addCircle(200F, 100F, 50F, Path.Direction.CW)
        addCircle(200F, 225F, 75F, Path.Direction.CW)
        addCircle(200F, 400F, 100F, Path.Direction.CW)
    }

    val pathDst = Path()
    val mMatrix = Matrix()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)
        rectFDst.set(500F, 50F, 800F, 150F)

        //снеговик
        p.color = Color.BLUE
        canvas.drawPath(path, p)

        //граница снеговика
        path.computeBounds(rectFBounds, true)
        p.color = Color.GREEN
        canvas.drawRect(rectFBounds, p)

        //START
        //рамка
        p.color = Color.BLACK
        canvas.drawRect(rectFDst, p)
        //преобразование
        mMatrix.reset()
        /*
            Методом setRectToRect настраиваем матрицу так, чтобы rectfBounds поместился в
                rectfDst. При этом необходимо указать ScaleToFit параметр. Параметр ScaleToFit
                позволяет указать, где разместить снеговика.
                - START – в левой (или верхней) стороне
                - CENTER – в центре
                - END – в правой (или нижней) стороне
                - FILL – не сохранять соотношение сторон, а растянуть первый прямоугольник так,
                   чтобы он полностью заполнил второй
         */
        mMatrix.setRectToRect(rectFBounds, rectFDst, Matrix.ScaleToFit.START)
        path.transform(mMatrix, pathDst)
        //снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)

        rectFDst.offset(0F, 150F)

        //CENTER
        //рамка
        p.color = Color.BLACK
        canvas.drawRect(rectFDst, p)
        //преобразование
        mMatrix.reset()
        mMatrix.setRectToRect(rectFBounds, rectFDst, Matrix.ScaleToFit.CENTER)
        path.transform(mMatrix, pathDst)
        //снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)

        rectFDst.offset(0F, 150F)

        //END
        //рамка
        p.color = Color.BLACK
        canvas.drawRect(rectFDst, p)
        //преобразование
        mMatrix.reset()
        mMatrix.setRectToRect(rectFBounds, rectFDst, Matrix.ScaleToFit.END)
        path.transform(mMatrix, pathDst)
        //снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)

        rectFDst.offset(0F, 150F)

        //FILL
        //рамка
        p.color = Color.BLACK
        canvas.drawRect(rectFDst, p)
        //преобразование
        mMatrix.reset()
        mMatrix.setRectToRect(rectFBounds, rectFDst, Matrix.ScaleToFit.FILL)
        path.transform(mMatrix, pathDst)
        //снеговик
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)
    }
}
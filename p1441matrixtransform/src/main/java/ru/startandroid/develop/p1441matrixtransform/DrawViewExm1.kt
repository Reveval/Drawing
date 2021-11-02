package ru.startandroid.develop.p1441matrixtransform

import android.content.Context
import android.graphics.*
import android.view.View

/*
    Воспользуемся возможностями Matrix - трансформация геометрических фигур. Сначала мы матрицу
        настраиваем, а потом применяем к какой-либо фигуре и фигура трансформируется согласно
        настройкам этой матрицы. В данном примере рассмотрим механизм перемещения объекта.
 */
class DrawViewExm1(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path()
    val mMatrix = Matrix()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        //создаем крест в path
        path.apply {
            reset()
            addRect(300F, 150F, 450F, 200F, Path.Direction.CW)
            addRect(350F, 100F, 400F, 250F, Path.Direction.CW)

            //рисуем path зеленым
            p.color = Color.GREEN
            canvas.drawPath(this, p)

            //настраиваем матрицу на перемещение на 300 вправо и 200 вниз
            mMatrix.reset()
            mMatrix.setTranslate(300F, 200F)

            /*
                Методом transform объекта path мы выполняем преобразование этого объекта согласно
                    переданной в этот метод матрице.
             */
            transform(mMatrix)

            //рисуем path синим
            p.color = Color.BLUE
            canvas.drawPath(this, p)
        }
    }
}
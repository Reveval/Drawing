package ru.startandroid.develop.p1451matrixtransform2

import android.content.Context
import android.graphics.*
import android.view.View

/*
    setPolyToPoly
        Мы передаем методу два массива координат: исходный и целевой. В исходном массиве
        содержатся координаты точек до выполнения преобразования. Т.е. как есть сейчас. А в
        целевом массиве мы задаем координаты, как должно быть после преобразования. И задача
        метода состоит в настройке матрицы так, чтобы она смогла выполнить такое преобразование:
        т.е. получить целевые точки из исходных.

    Одна точка
        Простой пример с одной точкой. В метод setPolyToPoly мы передаем исходный массив
        координат {100,100} и целевой массив координат {150,120}. Метод setPolyToPoly должен
        настроить матрицу так, чтобы после выполнения преобразования у нас то, что находится в
        точке (100,100) оказалось бы в точке (150,120). Т.е. в этом случае – это просто смещение
        на 50 вправо и на 20 вниз.
    Результат работы метода setPolyToPoly с одной точкой - это всегда перемещение объекта.
 */
class DrawViewExm2(context: Context) : View(context) {
    val p = Paint().apply {
        strokeWidth = 3F
        style = Paint.Style.STROKE
    }

    val path = Path()
    val pathDst = Path()
    val mMatrix = Matrix()

    val rectF = RectF(100F, 100F, 200F,200F)
    val src = arrayOf(100F, 200F).toFloatArray()
    val dst = arrayOf(150F, 120F).toFloatArray()

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        //зеленый квадрат
        path.reset()
        path.addRect(rectF, Path.Direction.CW)
        p.color = Color.GREEN
        canvas.drawPath(path, p)

        /*
            Далее используем метод setPolyToPoly. Он принимает 5 параметров:
            - массив исходных координат
            - позиция элемента в массиве исходных координат, с которого начинаем формировать точки
            - массив целевых координат
            - позиция элемента в массиве целевых координат, с которого начинаем формировать точки
            - кол-во точек, которые метод setPolyToPoly возьмет из массивов и использует для
            настройки матрицы
         */
        mMatrix.setPolyToPoly(src, 0, dst, 0, 1)
        path.transform(mMatrix, pathDst)

        //синий квадрат
        p.color = Color.BLUE
        canvas.drawPath(pathDst, p)
    }
}
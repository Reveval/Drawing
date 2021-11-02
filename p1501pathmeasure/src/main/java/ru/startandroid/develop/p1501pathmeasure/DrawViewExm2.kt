package ru.startandroid.develop.p1501pathmeasure

import android.content.Context
import android.graphics.*
import android.view.View

//попробуем получить геометрическую инфу о произвольной точке Path.
class DrawViewExm2(context: Context) : View(context) {
    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    val paintText = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 30F
    }

    //создаем Path, состоящий из нескольких линий и одной кривой
    val path = Path().apply {
        moveTo(100F, 300F)
        rLineTo(150F, 100F)
        rLineTo(150F, -100F)
        rQuadTo(150F, 200F, 300F, 0F)
        rLineTo(150F, 100F)
        rLineTo(150F, -100F)
    }

    /*
        Далее создаем для него PathMeasure, флаг forceClosed при этом ставим false – нам не нужно
            закрывать Path.
     */
    val pMeasure = PathMeasure(path, false)

    //Методом getLength получаем длину Path.
    val length = pMeasure.length
    val distance = length / 4

    val mMatrix = Matrix()
    val pos = FloatArray(2)
    val tan = FloatArray(2)
    val rect = Rect(-20, -10, 20, 10)

    init {
        /*
            Используем метод getMatrix, передаем в него:

                - расстояние от начала Path до точки, информация о которой нам необходима
                - матрицу, которая будет заполнена значениями, актуальными для указанной точки
                -  флаги. Их два POSITION_MATRIX_FLAG – в матрицу попадут данные только по позиции
                    точки, TANGENT_MATRIX_FLAG – в матрицу попадут данные только по повороту в
                    точке. Мы используем сразу оба флага.
            Тем самым мы получим матрицу, которая описывает положение и поворот объекта, который
                находится в точке на расстоянии distance от начала.
         */
        pMeasure.getMatrix(distance, mMatrix, PathMeasure.POSITION_MATRIX_FLAG +
                PathMeasure.TANGENT_MATRIX_FLAG)
        /*
            Метод getPosTan имеет схожий смысл, но он заполнит не матрицу, а два массива: pos –
                позиция, tan – наклон (cos и sin угла).
         */
        pMeasure.getPosTan(distance, pos, tan)
    }

    /*
        В методе onDraw рисуем Path, выводим значение distance и информацию, полученную из метода
            getPosTan. Далее применяем к канве матрицу, полученную из метода getMatrix и рисуем
            небольшой прямоугольник. Он разместится в точке, которая находится на расстоянии
            distance и его угол будет соответствовать углу наклона Path в этой точке.
     */
    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)
            drawPath(path, paint)
            drawText("Distance: $distance of $length", 100F, 100F, paintText)
            drawText("Position: ${pos.contentToString()}. Tangent (cos, sin): ${tan.
                contentToString()}", 100F, 150F, paintText)
            setMatrix(mMatrix)
            drawRect(rect, paint)
        } ?: return
    }
}
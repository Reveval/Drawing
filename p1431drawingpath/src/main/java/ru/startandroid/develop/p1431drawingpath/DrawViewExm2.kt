package ru.startandroid.develop.p1431drawingpath

import android.content.Context
import android.graphics.*
import android.view.View

//рисуем кривые линии при помощи path
class DrawViewExm2(context: Context) : View(context) {
    //флаг ANTI_ALIAS_FLAG сглаживает кривые при рисовании
    val p = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 3F
    }
    val path = Path()
    val point1 = Point(200, 300)
    val point21 = Point(500, 600)
    val point22 = Point(900, 200)

    override fun onDraw(canvas: Canvas?) {
        if (canvas == null) return
        canvas.drawARGB(80, 102, 204, 255)

        //первая линия
        p.color = Color.BLACK
        canvas.drawLine(100F, 100F, 600F, 100F, p)

        //точка отклонения для первой линии
        p.style = Paint.Style.FILL
        p.color = Color.GREEN
        canvas.drawCircle(point1.x.toFloat(), point1.y.toFloat(), 10F, p)

        //квадратичная кривая
        path.run {
            reset()
            moveTo(100F, 100F)
            /*
                рисуем кривую по тем же координатам, что и до этого использовали в отрисовке
                    черной линии но при этом задаем точку с координатами point1. Кривая
                    соответственно отклонится в сторону этой точки
             */
            quadTo(point1.x.toFloat(), point1.y.toFloat(), 600F, 100F)
            p.style = Paint.Style.STROKE
            canvas.drawPath(this, p)
        }

        //вторая линия
        p.color = Color.BLACK
        canvas.drawLine(400F, 400F, 1100F, 400F, p)

        //точки отклонения для второй линии
        p.style = Paint.Style.FILL
        p.color = Color.BLUE
        canvas.drawCircle(point21.x.toFloat(), point21.y.toFloat(), 10F, p)
        canvas.drawCircle(point22.x.toFloat(), point22.y.toFloat(), 10F, p)

        //кубическая кривая
        path.run {
            reset()
            moveTo(400F, 400F)
            //тут все аналогично зеленой кривой, но точек отклонения кривой две
            cubicTo(point21.x.toFloat(), point21.y.toFloat(), point22.x.toFloat(),
                point22.y.toFloat(), 1100F, 400F)
            p.style = Paint.Style.STROKE
            canvas.drawPath(this, p)
        }
    }
}
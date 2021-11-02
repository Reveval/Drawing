package ru.startandroid.develop.p1511patheffect

import android.content.Context
import android.graphics.*
import android.view.View

/*
    SumPathEffect и ComposePathEffect
        Позволяют нам комбинировать два эффекта, которые подаются им на вход.
        ComposePathEffect применит сначала один эффект, потом к получившемуся результату –
        второй и выведет результат. SumPathEffect – применит к искомой фигуре один эффект,
        выведет результат, затем применит к искомой фигуре второй эффект и выведет
        результат.
 */
class DrawViewExm5(context: Context) : View(context) {
    val path = Path().apply {
        addRect(-100F, 0F, 100F, 500F, Path.Direction.CW)
    }

    //эффект закругления
    val cornerEffect = CornerPathEffect(100F)
    //эффект прерывистой линии
    val dashPathEffect = DashPathEffect(arrayOf(20F, 5F).toFloatArray(), 0F)
    /*
        комбинация закругления и прерывистой линии. Сначала будет применен первый, затем
            к полученному результату - второй
     */
    val composeEffect = ComposePathEffect(dashPathEffect, cornerEffect)
    //линия сразу будет нарисованна комбинацией закругления и прерывистости
    val sumPathEffect = SumPathEffect(cornerEffect, dashPathEffect)

    //применяем эффекты выше к кистям разного цвета
    val paintWithoutEffects = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        strokeWidth = 3F
    }

    val paintWithCornerEffect = Paint(paintWithoutEffects).apply {
        color = Color.GREEN
        pathEffect = cornerEffect
    }

    val paintWithDashPathEffect = Paint(paintWithoutEffects).apply {
        color = Color.BLUE
        pathEffect = dashPathEffect
    }

    val paintWithComposeEffect = Paint(paintWithoutEffects).apply {
        color = Color.RED
        pathEffect = composeEffect
    }

    val paintWithSumPathEffect = Paint(paintWithoutEffects).apply {
        color = Color.YELLOW
        pathEffect = sumPathEffect
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.apply {
            drawARGB(80, 102, 204, 255)

            translate(120F, 100F)
            drawPath(path, paintWithoutEffects)

            translate(250F, 0F)
            drawPath(path, paintWithCornerEffect)

            translate(250F, 0F)
            drawPath(path, paintWithDashPathEffect)

            translate(250F, 0F)
            drawPath(path, paintWithComposeEffect)

            translate(250F, 0F)
            drawPath(path, paintWithSumPathEffect)
        } ?: return
    }
}
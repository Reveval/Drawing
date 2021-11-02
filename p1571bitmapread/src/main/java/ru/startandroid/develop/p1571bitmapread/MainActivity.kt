package ru.startandroid.develop.p1571bitmapread

import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

/*
     рассмотрим все основные операции с Bitmap. Bitmap – это объект, который хранит в себе
        изображение. Та же канва, с которой мы обычно работаем, это обертка, которая принимает
        команды от нас и рисует их на Bitmap, который мы видим в результате.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawView(this))
    }

    class DrawView(context: Context) : View(context) {
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)

        /*
            Получаем Bitmap из drawable-ресурса. На вход методу decodeResource мы передали
                объект ресурсов и ID требуемого ресурса.
         */
        private val bitmap = BitmapFactory.decodeResource(resources, R.drawable.icon)
            .apply {
            /*
                Далее в переменную info сформируем строку с инфой о картинке:
                    - getWidth – ширина картинки в px
                    - getHeight – высота картинки в px
                    - getByteCount – число байт, которое занимает картинка
                    - getRowBytes – число байт в одной строке картинки
                    - getConfig – инфа о способе хранения данных о пикселах
             */
            val info = "Info: size = $width x $height, " +
                    "bytes = $byteCount($rowBytes), " +
                    "config = $config"
            Log.d("myLogs", info)
        }

        /*
            Настраиваем матрицу, которая повернет картинку на 45 градусов, растянет картинку
                в два раза в ширину и в три раза в высоту, и переместит ее на 200 вправо
                и 50 вниз.
         */
        val mMatrix = Matrix().apply {
            postRotate(45F)
            postScale(2F, 3F)
            postTranslate(200F, 50F)
        }

        /*
            Создаем два Rect объекта. rectSrc со сторонами равными половине сторон картинки.
            Т   .е. этот прямоугольник охватывает левую верхнюю четверть картинки. Эту часть
            мы будем брать для вывода на экран далее в примере. А выводить мы ее будем в
            прямоугольник rectDst, это просто произвольная область на экране.
         */
        val rectSrc = Rect(0, 0, bitmap.width / 2, bitmap.height / 2)
        val rectDst = Rect(300, 100, 500, 200)

        /*
            В методе onDraw рисуем картинку на канве тремя разными версиями метода drawBitmap.
                В первом случае просто выводим картинку как есть в точке (50,50). Во втором
                применяем матрицу, в которой мы уже настроили поворот, трансформацию и
                перемещение. И третий вариант возьмет от картинки часть, входящую в область
                rectSrc (мы там задали левую верхнюю четверть) и нарисует ее на канве в
                области rectDst, применив необходимые трансформации и перемещения.
         */
        override fun onDraw(canvas: Canvas?) {
            canvas?.apply {
                drawARGB(80, 102, 204, 255)
                drawBitmap(bitmap, 50F, 50F, paint)
                drawBitmap(bitmap, mMatrix, paint)
                drawBitmap(bitmap, rectSrc, rectDst, paint)
            }
        }
    }
}
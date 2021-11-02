package ru.startandroid.develop.p1601bitmaplarge

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.ImageView
import java.io.File

const val LOG_TAG = "myLogs"

//читаем и отображаем большие изображения
class MainActivity : AppCompatActivity() {
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        logMemory()
        readImage()
        logMemory()
    }

    //Метод readImage – читает Bitmap из файла и отображает его в ImageView.
    private fun readImage() {
        val px = resources.getDimensionPixelSize(R.dimen.image_size)
        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), "map.jpeg")
        val bitmap = decodeSampledBitmapFromResource(file.absolutePath, px, px)
        Log.d(LOG_TAG, "Required size = $px, bitmap size = ${bitmap.width}x" +
                "${bitmap.height}, byteCount = ${bitmap.byteCount}")
        imageView.setImageBitmap(bitmap)
    }

    /*
        Метод logMemory показывает сколько КБ памяти занимает наше приложение. Будем вызывать этот
            метод до и после отображения, чтобы увидеть сколько памяти заняла картинка.
     */
    private fun logMemory() {
        Log.d(LOG_TAG, "Total memory = ${Runtime.getRuntime().totalMemory() / 1024}")
    }

    companion object {
        /*
            В методе decodeSampledBitmapFromResource мы просто читаем изображение с включенным
                inJustDecodeBounds. Тем самым, в options помещаются данные о размере изображения,
                но само изображение не читается.
         */
        fun decodeSampledBitmapFromResource(path: String, reqWidth: Int, reqHeight: Int) : Bitmap {
            //Читаем с inJustDecodeBounds=true для определения размеров
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(path, options)

            /*
                Далее объект options с данными о размере изображения мы передаем в метод
                    calculateInSampleSize. А также передаем туда ширину и высоту изображения,
                    которые нам нужно получить на выходе.
             */
            options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight)

            /*
                Далее мы отключаем inJustDecodeBounds и получаем bitmap, который будет уменьшен
                    до необходимых нам размеров.
             */
            options.inJustDecodeBounds = false
            /*
                Нашему изображению не нужна прозрачность. Поэтому мы можем использовать RGB_565
                    конфигурацию вместо дефолтовой ARGB_8888. Это уменьшит вес bitmap еще в два раза
             */
            options.inPreferredConfig = Bitmap.Config.RGB_565
            return BitmapFactory.decodeFile(path, options)
        }

        /*
            Метод calculateInSampleSize вычисляет (и помещает в inSampleSize) коэффициент
                уменьшения изображения. Метод на вход получает объект options, который содержит
                данные о реальном размере изображения. Также на вход идут reqWidth и reqHeight,
                в которые мы передаем желаемые нам размеры изображения.
         */
        fun calculateInSampleSize(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int)
        : Int {
            //реальные размеры изображения
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1

            if (height > reqHeight || width > reqWidth) {
                val halfHeight = height / 2
                val halfWidth = width / 2

                /*
                    Вычисляем наибольший inSampleSize, который будет кратным двум и оставит
                        полученные размеры больше, чем требуемые
                 */
                while ((halfHeight / inSampleSize) > reqHeight &&
                    (halfWidth / inSampleSize) > reqWidth) {
                    inSampleSize *= 2
                }
            }
            return inSampleSize
        }
    }
}
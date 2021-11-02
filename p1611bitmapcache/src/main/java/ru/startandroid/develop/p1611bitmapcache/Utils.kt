package ru.startandroid.develop.p1611bitmapcache

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class Utils {
    companion object {
        fun decodeSampledBitmapFromResource(path: String?, reqWidth: Int, reqHeight: Int) : Bitmap {
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
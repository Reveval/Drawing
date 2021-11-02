package ru.startandroid.develop.p1611bitmapcache

import android.content.Context
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import androidx.core.util.lruCache
import com.squareup.picasso.Picasso
import java.io.File

//используем memory-кэш и библиотеку Picasso
class MainActivity : AppCompatActivity() {
    lateinit var listView: ListView

    /*
        В onCreate мы из папки Download/L0161 читаем все файлы в массив и передаем этот
            массив адаптеру.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.lvImages)

        val dir = File(getExternalFilesDir(null), "Download/L0161")
        val fileArray = dir.listFiles()

        if (fileArray != null) {
            listView.adapter = ImageAdapter(this, fileArray)
        }
    }

    //исходный ImageAdapter
    /*class ImageAdapter(context: Context, objects: Array<File>) :
        ArrayAdapter<File>(context, R.layout.list_item, objects) {
        val mInflater = LayoutInflater.from(context)
        val size = context.resources.getDimensionPixelSize(R.dimen.image_size)
        var memoryCache: LruCache<String, Bitmap>

        init {
            *//*
                определяем максимально доступное приложению кол-во памяти, делим на 8 и полученный
                    результат используем как максимальный размер кэша.
             *//*
            val cacheSize = ((Runtime.getRuntime().maxMemory()) / 8).toInt()

            memoryCache = object : LruCache<String, Bitmap>(cacheSize) {
                *//*
                    Переопределяем метод sizeOf, и в нем в качестве размера будем возвращать
                        реальное кол-во байтов в Bitmap. Т.е. суммарное кол-во байтов всех Bitmap
                        в кэше не должно превысить тот максимальный размер, который мы указали
                        при создании кэша.
                 *//*
                override fun sizeOf(key: String?, value: Bitmap?): Int {
                    return value?.byteCount ?: 0
                }
            }
        }

        *//*
            В ImageAdapter в методе getView мы вызываем метод getBitmap, чтобы получить Bitmap
                для определенного элемента списка и отображаем этот Bitmap в ImageView.
         *//*
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if (view == null) {
                view = mInflater.inflate(R.layout.list_item, parent, false)

            }
            val imageView = view?.findViewById<ImageView>(R.id.imageView)
            val bitmap = getBitmap(position)
            imageView?.setImageBitmap(bitmap)
            return view!!
        }

        *//*
            В методе getBitmap мы теперь не просто читаем изображение с SD, а сначала пытаемся
                достать его из кэша. Если в кэше его нет, то читаем с диска и помещаем в кэш.
         *//*
        private fun getBitmap(position: Int) : Bitmap {
            val filePath = getItem(position)?.absolutePath
            var bitmap = getBitmapFromMemCache(filePath)
            if (bitmap == null) {
                bitmap = Utils.decodeSampledBitmapFromResource(filePath, size, size)
                addBitmapToMemoryCache(filePath, bitmap)
            }
            return bitmap
        }

        *//*
            Метод addBitmapToMemoryCache проверяет, что в кэше еще нет такого значения, и помещает
                его туда методом put.
         *//*
        fun addBitmapToMemoryCache(key: String?, bitmap: Bitmap) {
            if (getBitmapFromMemCache(key) == null) {
                memoryCache.put(key, bitmap)
            }
        }

        *//*
            getBitmapFromMemCache просто возвращает нам по ключу значение из кэша, используя для
                этого метод get.
         *//*
        fun getBitmapFromMemCache(key: String?) : Bitmap? {
            return memoryCache.get(key)
        }
    }*/

    /*
        ImageAdapter с использованием библиотеки Picasso. Мы создаем экземпляр Picasso, а в
            getView используем его методы:
                - load – указываем File-объект (есть и другие реализации этого метода, в т.ч. можно
                    передавать веб-ссылку на файл)
                - resizeDimen – просим привести размер изображения к требуемому нам
                - centerInside – изображение будет втиснуто (а не обрезано или растянуто) в
                    указанный нами (в resizeDimen) размер
                - into – ImageView в котором надо отобразить изображение
     */
    class ImageAdapter(context: Context, objects: Array<File>) :
        ArrayAdapter<File>(context, R.layout.list_item, objects) {
        val mInflater = LayoutInflater.from(context)
        val picasso = Picasso.Builder(context).build()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            var view = convertView
            if (view == null) {
                view = mInflater.inflate(R.layout.list_item, parent, false)

            }
            val imageView = view?.findViewById<ImageView>(R.id.imageView)
            getItem(position)?.let {
                picasso.load(it)
                    .resizeDimen(R.dimen.image_size, R.dimen.image_size)
                    .centerInside()
                    .into(imageView)
            }
            return view!!
        }
    }
}
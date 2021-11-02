package ru.startandroid.develop.p1411canvasview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.View

//Рассмотрим обычное 2D-рисование при помощи Canvas
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(DrawView(this))
    }

    /*
        DrawView является наследником SurfaceView и заодно реализует интерфейс обработчика
            SurfaceHolder.Callback. SurfaceView только отображает контент. А работа с ним ведется
            через обработчика SurfaceHolder.
     */
    class DrawView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {
        private lateinit var drawThread: DrawThread

        init {
            /*
                Мы получаем SurfaceHolder и сообщаем ему, что сами будем обрабатывать его события.
                    Таких событий три:
                    - surfaceChanged - был изменен формат или размер SurfaceView
                    - surfaceCreated – SurfaceView создан и готов к отображению информации
                    - surfaceDestroyed – вызывается перед тем, как SurfaceView будет уничтожен
             */
            holder.addCallback(this)
        }

        /*
            В surfaceCreated мы создаем свой поток прорисовки, передаем ему SurfaceHolder. Вызовом
                метода setRunning(true) ставим ему метку о том, что он может работать и стартуем его.
         */
        override fun surfaceCreated(holder: SurfaceHolder) {
            drawThread = DrawThread(getHolder())
            drawThread.run {
                running = true
                start()
            }
        }

        override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

        /*
            В surfaceDestroyed мы своему потоку сообщаем (setRunning(false)) о том, что его работа
                должна быть прекращена, т.к. SurfaceView сейчас будет уничтожено. Далее запускаем
                цикл, который ждет, пока не завершит работу наш поток прорисовки. Дождаться надо
                обязательно, иначе поток может попытаться нарисовать что-либо
                на уничтоженном SurfaceView.
         */
        override fun surfaceDestroyed(holder: SurfaceHolder) {
            var retry = true
            drawThread.running = false
            while (retry) {
                try {
                    drawThread.join()
                    retry = false
                } catch (ex: InterruptedException) {}
            }
        }

        /*
            DrawThread, наследник Thread, – это наш поток прорисовки. В нем и будет происходить
                рисование. В конструктор передаем SurfaceHolder. Он нам нужен, чтобы добраться до
                канвы.
         */
        class DrawThread(surfaceHolder: SurfaceHolder) : Thread() {
            private val mSurfaceHolder = surfaceHolder
            //Свойство running ставит метку работы, сообщающую потоку, можно ли работать.
            var running: Boolean = false

            /*
                Метод run. В нем видим цикл, который выполняется пока позволяет метка работы
                    (running). В цикле обнуляем переменную канвы, затем от SurfaceHolder получаем
                    канву методом lockCanvas. На всякий случай проверяем, что канва не null, и
                    можно рисовать: снова просто закрашиваем все зеленым цветом. После того, как
                    нарисовали, что хотели, мы возвращаем канву объекту SurfaceHolder методом
                    unlockCanvasAndPost в секции finally (обязательной для выполнения) и
                    SurfaceView отобразит наши художества.
             */
            override fun run() {
                var canvas: Canvas? = null
                while (running) {
                    try {
                        canvas = mSurfaceHolder.lockCanvas(null)
                        if (canvas == null) continue
                        canvas.drawColor(Color.RED)
                    } finally {
                        if (canvas != null) {
                            mSurfaceHolder.unlockCanvasAndPost(canvas)
                        }
                    }
                }
            }
        }
    }
}
package ru.startandroid.develop.p1661customdrawable

class ColorHexagonDrawable(color: Int) : HexagonDrawable() {
    init {
        paint.color = color
    }
}
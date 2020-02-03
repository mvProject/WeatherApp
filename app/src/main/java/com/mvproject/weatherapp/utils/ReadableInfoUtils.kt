package com.mvproject.weatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
// todo: supressing insted of SimpleDateFormat("HH:mm", Locale.getDefault())
fun  Int.getTime(): String {
    // todo: hardcode
    return SimpleDateFormat("HH:mm").format(Date(this*1000L)) // todo: this*1000L - wtf? :)

}

fun Double.getTempCelsius() : String{
    return this.toString()
}

// todo: interesting :)
fun String.getImageFullUrl() : String{
    return "http://openweathermap.org/img/w/$this.png"
}


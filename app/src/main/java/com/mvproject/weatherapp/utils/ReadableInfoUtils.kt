package com.mvproject.weatherapp.utils

import com.mvproject.weatherapp.R
import splitties.init.appCtx
import java.text.SimpleDateFormat
import java.util.*

fun  Int.getTime(): String {
    return SimpleDateFormat(appCtx.getString(R.string.date_format_pattern),Locale.getDefault()).format(this)
}

fun Double.getTempCelsius() : String{
    return this.toString()
}

// todo: interesting :)
fun String.getImageFullUrl() : String{
    return "http://openweathermap.org/img/w/$this.png"
}


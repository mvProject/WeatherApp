package com.mvproject.weatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun  Int.getDate(): String {
    return SimpleDateFormat("dd.MM.yyyy").format(Date(this*1000L))
}
@SuppressLint("SimpleDateFormat")
fun  Int.getTime(): String {
    return SimpleDateFormat("HH:mm").format(Date(this*1000L))
}

fun Double.getTempCelsius() : String{
    return this.toString()
}


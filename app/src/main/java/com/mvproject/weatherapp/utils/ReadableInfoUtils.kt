package com.mvproject.weatherapp.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun  Long.getDate(): String {
    return SimpleDateFormat("dd.MM.yyyy").format(Date(this*1000L))
}
@SuppressLint("SimpleDateFormat")
fun  Long.getTime(): String {
    return SimpleDateFormat("HH:mm").format(Date(this*1000L))
}

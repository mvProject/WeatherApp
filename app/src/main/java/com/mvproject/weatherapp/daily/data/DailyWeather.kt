package com.mvproject.weatherapp.daily.data

data class DailyWeather (
    val time: String,
    val name: String,
    val tempMin: String,
    val tempMax: String,
    val weatherDescription: String,
    val weatherIcon: String
)
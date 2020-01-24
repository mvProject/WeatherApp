package com.mvproject.weatherapp.weekly.data

data class WeatherInfo(
    val dt: Int,
    val dt_txt: String,
    val main: Main,
    val weather: List<Weather>,
    val wind: Wind
)
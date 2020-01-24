package com.mvproject.weatherapp.weekly.data

data class WeeklyWeather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherInfo>,
    val message: Double
)
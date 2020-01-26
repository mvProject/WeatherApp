package com.mvproject.weatherapp.daily.data

import com.mvproject.weatherapp.weekly.data.Main
import com.mvproject.weatherapp.weekly.data.Weather

data class CurrentWeather(
    val dt: Int,
    val main: Main,
    val name: String,
    val weather: List<Weather>
)

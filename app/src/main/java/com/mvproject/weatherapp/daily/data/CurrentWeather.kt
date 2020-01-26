package com.mvproject.weatherapp.daily.data

import com.mvproject.weatherapp.utils.getTempCelsius
import com.mvproject.weatherapp.utils.getTime
import com.mvproject.weatherapp.weekly.data.Main
import com.mvproject.weatherapp.weekly.data.Weather

data class CurrentWeather(
    val dt: Int,
    val main: Main,
    val name: String,
    val weather: List<Weather>
)
{
    fun getReadableTime() = dt.getTime()

    fun getReadableDescription() = weather.first().description

    fun getTempMinCels() = main.temp_min.getTempCelsius()

    fun getTempMaxCels() = main.temp_max.getTempCelsius()
}
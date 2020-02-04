package com.mvproject.weatherapp.network

import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.weekly.data.WeeklyWeather
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("forecast/")
    fun fewForecastAsync(
        @Query("lat") lat : String,
        @Query("lon") lon : String) : Deferred<WeeklyWeather>

    @GET("weather/")
    fun dailyForecastAsync(
        @Query("lat") lat : String,
        @Query("lon") lon : String) : Deferred<CurrentWeather>
}
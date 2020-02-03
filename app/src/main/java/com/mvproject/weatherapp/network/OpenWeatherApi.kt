package com.mvproject.weatherapp.network

import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.weekly.data.WeeklyWeather
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

// todo: what will happen if you will expand app up to 20 screens with different api call and then suddenly need to switch api account (api key)
interface OpenWeatherApi {
    @GET("forecast/")
    fun fewForecastAsync(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("units") units : String = "metric",
        @Query("appId") appId : String = "4a0e92edc8279c01a9d8c70a81624123") : Deferred<WeeklyWeather>

    @GET("weather/")
    fun dailyForecastAsync(
        @Query("lat") lat : String,
        @Query("lon") lon : String,
        @Query("units") units : String = "metric",
        @Query("appId") appId : String = "4a0e92edc8279c01a9d8c70a81624123") : Deferred<CurrentWeather>
}
package com.mvproject.weatherapp.network

class WeatherDataLoad {
    private val api = ApiService().initApi()

    suspend fun getWeatherData(lat: String,lon : String) = api.fewForecastAsync(lat,lon).await()

    suspend fun getSingleWeatherData(lat: String,lon : String) = api.dailyForecastAsync(lat,lon).await()
}
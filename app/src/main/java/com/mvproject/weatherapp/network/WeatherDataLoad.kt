package com.mvproject.weatherapp.network

// todo: good approache to use that class, but naming bad
// todo: better to create interface IWeatherRepository with some method as getWeeklyWeather
// then build a class WeatherRepository(api: Api): IWeatherRepository, that will handle logic of request weather
// so you will be able to quickly switch api, add database and so on
class WeatherDataLoad {
    private val api = ApiService().initApi()

    suspend fun getWeeklyWeatherData(lat: String,lon : String) = api.fewForecastAsync(lat,lon).await()

    suspend fun getSingleWeatherData(lat: String,lon : String) = api.dailyForecastAsync(lat,lon).await()
}
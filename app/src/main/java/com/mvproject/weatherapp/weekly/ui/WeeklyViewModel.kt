package com.mvproject.weatherapp.weekly.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvproject.weatherapp.network.WeatherDataLoad
import com.mvproject.weatherapp.utils.getImageFullUrl
import com.mvproject.weatherapp.weekly.data.Forecast
import kotlinx.coroutines.*

class WeeklyViewModel : ViewModel() {
    private var myJob: Job? = null
    var forecast = MutableLiveData<MutableList<Forecast>>()

    fun getWeeklyData(lat : String?,lon : String?){
        if (lat == null && lon ==null) {
            Log.d("Weather", "coords empty")
        }
        else{
            myJob = CoroutineScope(Dispatchers.IO).launch {
                val weather = WeatherDataLoad().getWeeklyWeatherData(lat!!,lon!!)
                withContext(Dispatchers.Main) {
                    val list = mutableListOf<Forecast>()
                    for (item in weather.list)
                        list.add(
                            Forecast(
                                item.dt_txt,
                                item.main.humidity.toString(),
                                item.main.pressure.toString(),
                                item.main.temp.toString(),
                                item.wind.deg.toString(),
                                item.wind.speed.toString(),
                                item.weather.first().icon.getImageFullUrl()
                            )
                        )
                    forecast.value = list
                }
            }
        }
    }
}

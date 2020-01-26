package com.mvproject.weatherapp.daily.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.network.WeatherDataLoad
import kotlinx.coroutines.*

class DailyViewModel : ViewModel() {

    private var myJob: Job? = null
    val singleWeather = MutableLiveData<CurrentWeather>()

    fun getData(lat : String,lon : String){

        myJob = CoroutineScope(Dispatchers.IO).launch {
            val weather = WeatherDataLoad()
                .getSingleWeatherData(lat,lon)
            withContext(Dispatchers.Main) {
                singleWeather.value = weather
            }
        }
    }
}

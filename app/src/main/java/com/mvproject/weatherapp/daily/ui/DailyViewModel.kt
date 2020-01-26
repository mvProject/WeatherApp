package com.mvproject.weatherapp.daily.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.network.WeatherDataLoad
import kotlinx.coroutines.*

class DailyViewModel : ViewModel() {

    private var myJob: Job? = null
    val singleWeather = MutableLiveData<CurrentWeather>()

    fun getData(){
        Log.d("Weather","start daily")

        // Todo get coords from GPS

        myJob = CoroutineScope(Dispatchers.IO).launch {
            val weather = WeatherDataLoad()
                .getSingleWeatherData("48.5132","32.2597")
            withContext(Dispatchers.Main) {
                singleWeather.value = weather
                Log.d("Weather","end daily")
                Log.d("Weather","viewmodel singleWeather name - " + singleWeather.value)


            }
        }
    }
}

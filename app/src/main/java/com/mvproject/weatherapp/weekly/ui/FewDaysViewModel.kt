package com.mvproject.weatherapp.weekly.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mvproject.weatherapp.network.WeatherDataLoad
import kotlinx.coroutines.*

class FewDaysViewModel() : ViewModel() {
    // TODO: Implement the ViewModel
    private var myJob: Job? = null

    fun getWeeklyData(){
        Log.d("Weather","start weekly")

        // Todo get coords from GPS

        myJob = CoroutineScope(Dispatchers.IO).launch {
            //val weather = WeatherDataLoad().getWeatherData(lat,lon)
            val weather = WeatherDataLoad()
                .getWeatherData("48.5132","32.2597")
            withContext(Dispatchers.Main) {
                Log.d("Weather","end weekly")
                Log.d("Weather","viewmodel weatherWeekly name - " + weather.city.name)
            }
        }
    }
}

package com.mvproject.weatherapp.daily.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.network.WeatherDataLoad
import kotlinx.coroutines.*

class DailyViewModel : ViewModel() {

    private var myJob: Job? = null
    val singleWeather = MutableLiveData<CurrentWeather>()

    fun getData(lat : String?,lon : String?){
        if (lat == null && lon ==null) {
            Log.d("Weather", "coords empty")
        }
        else{
            myJob = CoroutineScope(Dispatchers.IO).launch {
                val weather = WeatherDataLoad()
                    .getSingleWeatherData(lat!!,lon!!)
                withContext(Dispatchers.Main) {
                    singleWeather.value = weather
                }
            }
        }
    }
}

package com.mvproject.weatherapp.weekly.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mvproject.weatherapp.network.WeatherDataLoad
import com.mvproject.weatherapp.utils.getImageFullUrl
import com.mvproject.weatherapp.weekly.data.Forecast
import kotlinx.coroutines.*

class FewDaysViewModel : ViewModel() {
    private var myJob: Job? = null
    var forecast = MutableLiveData<MutableList<Forecast>>()

    fun getWeeklyData(){
        Log.d("Weather","start weekly")

        // Todo get coords from GPS

        myJob = CoroutineScope(Dispatchers.IO).launch {
            val weather = WeatherDataLoad().getWeeklyWeatherData("48.5132","32.2597")
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
                Log.d("Weather","end weekly")
                Log.d("Weather","viewmodel weatherWeekly name - " + forecast.value)
            }
        }
    }
}

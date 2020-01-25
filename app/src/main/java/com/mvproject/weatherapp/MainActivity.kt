package com.mvproject.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mvproject.weatherapp.network.WeatherDataLoad
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private var myJob: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_dashboard
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

       // getData()
    }

    private fun getData(){
        Log.d("Weather","start")

        myJob = CoroutineScope(Dispatchers.IO).launch {
            //val weather = WeatherDataLoad().getWeatherData(lat,lon)
            val weather = WeatherDataLoad()
                .getWeatherData("48.5132","32.2597")
            val singleWeather = WeatherDataLoad()
                .getSingleWeatherData("48.5132","32.2597")
            withContext(Dispatchers.Main) {
                Log.d("Weather","end")
                Log.d("Weather","weather name - " + weather.city.name)
                Log.d("Weather","singleWeather name - " + singleWeather.name)

            }
        }
    }

}

package com.mvproject.weatherapp.daily.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.daily.data.DailyWeather
import com.mvproject.weatherapp.databinding.DailyFragmentBinding
import com.mvproject.weatherapp.utils.getImageFullUrl
import com.mvproject.weatherapp.utils.getTempCelsius
import com.mvproject.weatherapp.utils.getTime

class DailyFragment : Fragment() {

    private lateinit var dailyViewModel: DailyViewModel
    private lateinit var dailyBinding: DailyFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        dailyBinding = DailyFragmentBinding.inflate(inflater,container,false)
        return  dailyBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        dailyViewModel = ViewModelProvider(this).get(DailyViewModel::class.java)

        dailyBinding.lifecycleOwner = this

        dailyViewModel.singleWeather.observe(viewLifecycleOwner,Observer<CurrentWeather>{
            val dailyWeather = DailyWeather(it.dt.getTime(),
                                       it.name,
                                       it.main.temp_min.getTempCelsius(),
                                       it.main.temp_min.getTempCelsius(),
                                       it.weather.first().description,
                                       it.weather.first().icon.getImageFullUrl()
                )
            dailyBinding.dailyWeather = dailyWeather
        })

        dailyViewModel.getData(arguments?.getString("lat"),arguments?.getString("lon"))
    }

}

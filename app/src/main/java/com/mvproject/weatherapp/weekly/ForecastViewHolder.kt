package com.mvproject.weatherapp.weekly

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.weatherapp.R
import com.mvproject.weatherapp.databinding.WeatherListItemBinding
import com.mvproject.weatherapp.weekly.data.Forecast

class ForecastViewHolder(private val parent: ViewGroup, private val binding: WeatherListItemBinding = DataBindingUtil.inflate(
    LayoutInflater.from(parent.context), R.layout.weather_list_item,parent,false)
) : RecyclerView.ViewHolder(binding.root) {

    fun bindItem(weather: Forecast) {
        binding.forecastItem = weather
    }
}
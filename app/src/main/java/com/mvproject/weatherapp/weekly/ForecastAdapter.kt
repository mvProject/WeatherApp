package com.mvproject.weatherapp.weekly

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvproject.weatherapp.weekly.data.Forecast

class ForecastAdapter(var forecast: MutableList<Forecast>) : RecyclerView.Adapter<ForecastViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {

        return ForecastViewHolder(parent)
    }

    override fun getItemCount(): Int {
        return forecast.size
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bindItem(forecast[position])
    }
}
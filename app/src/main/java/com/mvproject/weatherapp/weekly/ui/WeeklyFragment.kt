package com.mvproject.weatherapp.weekly.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvproject.weatherapp.R
import com.mvproject.weatherapp.weekly.recyclerview.ForecastAdapter
import com.mvproject.weatherapp.weekly.data.Forecast
import kotlinx.android.synthetic.main.weekly_fragment.*

class WeeklyFragment : Fragment() {

    private lateinit var weeklyViewModel: WeeklyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.weekly_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        weeklyViewModel = ViewModelProvider(this).get(WeeklyViewModel::class.java)

        weeklyViewModel.forecast.observe(viewLifecycleOwner, Observer<MutableList<Forecast>> {
            it.let{forecastList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ForecastAdapter(it)
            }}
        })

        weeklyViewModel.getWeeklyData(arguments?.getString("lat"),arguments?.getString("lon"))
    }

}

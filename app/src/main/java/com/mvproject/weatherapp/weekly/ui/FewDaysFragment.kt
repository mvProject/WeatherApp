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
import kotlinx.android.synthetic.main.few_days_fragment.*


class FewDaysFragment : Fragment() {

    private lateinit var viewModel: FewDaysViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.few_days_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(FewDaysViewModel::class.java)

        viewModel.forecast.observe(viewLifecycleOwner, Observer<MutableList<Forecast>> {
            it.let{forecastList.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = ForecastAdapter(it)
            }}
        })

        viewModel.getWeeklyData(arguments?.getString("lat")?: "48.5132",
                               arguments?.getString("lon")?: "32.2597")
    }

}

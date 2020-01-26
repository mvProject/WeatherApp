package com.mvproject.weatherapp.daily.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.mvproject.weatherapp.R
import com.mvproject.weatherapp.daily.data.CurrentWeather
import com.mvproject.weatherapp.databinding.DailyFragmentBinding

class DailyFragment : Fragment() {

    private lateinit var viewModel: DailyViewModel
    private lateinit var dailyBinding: DailyFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?
    ): View? {
        dailyBinding = DailyFragmentBinding.inflate(inflater,container,false)

        return  dailyBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DailyViewModel::class.java)

        dailyBinding.lifecycleOwner = this

        viewModel.singleWeather.observe(viewLifecycleOwner,Observer<CurrentWeather>{
            dailyBinding.dailyWeather = it
        })

        viewModel.getData()
    }

}

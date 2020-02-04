package com.mvproject.weatherapp.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mvproject.weatherapp.utils.getApiInterceptor
import com.mvproject.weatherapp.utils.getCache
import com.mvproject.weatherapp.utils.getCachingInterceptor
import com.mvproject.weatherapp.utils.getLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiService {

    fun initApi() : OpenWeatherApi {

        val okHttpClient = OkHttpClient.Builder()
            .cache(getCache())
            .addInterceptor(getCachingInterceptor())
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor(getApiInterceptor())
            .build()
        return Retrofit.Builder().apply {
            baseUrl(BASE_URL)
            addCallAdapterFactory(CoroutineCallAdapterFactory())
            addConverterFactory(GsonConverterFactory.create())
            client(okHttpClient)
        }.build().create(OpenWeatherApi::class.java)
    }
    companion object {
        const val BASE_URL = "http://api.openweathermap.org/data/2.5/"
    }
}
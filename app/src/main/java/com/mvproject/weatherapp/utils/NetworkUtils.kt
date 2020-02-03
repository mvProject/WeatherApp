package com.mvproject.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import splitties.init.appCtx

fun getLoggingInterceptor(): HttpLoggingInterceptor {
    val logs = HttpLoggingInterceptor()
    logs.level = HttpLoggingInterceptor.Level.BODY
    return logs
}

fun getCachingInterceptor(): Interceptor {
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = if (hasNetwork(appCtx)!!) {
                Log.d("Weather", "data from internet")
                // todo: hardcoded string args
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()

            } else {
                Log.d("Weather", "data from cache")
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7 // todo: magic numbers
                ).build()
            }

            // todo: crash Android 9, API 28, Xiaomi Mi A2
            /*
             *2020-01-31 09:08:04.078 15485-15539/com.mvproject.weatherapp E/AndroidRuntime: FATAL EXCEPTION: DefaultDispatcher-worker-1
    Process: com.mvproject.weatherapp, PID: 15485
    java.net.UnknownServiceException: CLEARTEXT communication to api.openweathermap.org not permitted by network security policy
             */
            return chain.proceed(request)
        }
    }
}

fun getCache(): Cache {
    // todo: magic numbers
    val cacheSize = (5 * 1024 * 1024).toLong()
    return Cache(appCtx.cacheDir, cacheSize)
}

// todo: suppressed warning instead of handling old API
@Suppress("DEPRECATION")
fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false // Initial Value // todo: redundant comment
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    Log.d("Weather", "Internet available $isConnected")
    return isConnected
}

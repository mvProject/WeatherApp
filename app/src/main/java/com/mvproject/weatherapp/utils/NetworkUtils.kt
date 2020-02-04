package com.mvproject.weatherapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.mvproject.weatherapp.BuildConfig
import com.mvproject.weatherapp.R
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
                Log.d(appCtx.getString(R.string.log_tag), appCtx.getString(R.string.data_from_inet))
                // todo: hardcoded string args
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()

            } else {
                Log.d(appCtx.getString(R.string.log_tag), appCtx.getString(R.string.data_from_cache))
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 604800// todo: magic numbers
                ).build()
            }
            return chain.proceed(request)
        }
    }
}

fun getApiInterceptor(): Interceptor {
    return object : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val url = chain.request().url.newBuilder().
                addQueryParameter("APPID", BuildConfig.OPEN_WEATHER_API_KEY).
                addQueryParameter("units", "metric").build()
            return chain.proceed(chain.request().newBuilder().url(url).build())
        }
    }
}

fun getCache(): Cache {
    return Cache(appCtx.cacheDir, 5242880)
}

// todo: suppressed warning instead of handling old API
@Suppress("DEPRECATION")
fun hasNetwork(context: Context): Boolean? {
    var isConnected: Boolean? = false
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    Log.d(appCtx.getString(R.string.log_tag), "Internet available $isConnected")
    return isConnected
}

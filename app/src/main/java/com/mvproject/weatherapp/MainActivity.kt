package com.mvproject.weatherapp

import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mvproject.weatherapp.utils.permissions.PermissionCheck

class MainActivity : AppCompatActivity() {

    private var locationManager : LocationManager? = null
    private val bundle = Bundle()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_daily,
                R.id.navigation_weekly
            )
        )

        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager?

        val locationListener: LocationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
               bundle.putString("lat", location.latitude.toString())
               bundle.putString("lon", location.longitude.toString())
               navController.navigate(R.id.navigation_daily,bundle)
             }
            override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}
        }

        val permissionsNeeded= intArrayOf(1,2)
        try {
            if (PermissionCheck.checkForPermissions(this, permissionsNeeded)) {
                locationManager?.requestSingleUpdate(
                    LocationManager.GPS_PROVIDER,
                    locationListener,
                    null
                )
            }
        } catch(ex: SecurityException) {
                Log.d("Weather", "Security Exception, no location available")

        }

        navView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navigation_daily -> {
                    Log.d("Weather", "daily clicked")
                    navController.navigate(R.id.navigation_daily,bundle)
                    true
                }

                R.id.navigation_weekly -> {
                    Log.d("Weather", "weekly clicked")
                    navController.navigate(R.id.navigation_weekly,bundle)
                    true
                }
                else -> true
            }
        }
    }
}

package com.mvproject.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.location.aravind.getlocation.GeoLocator
import com.mvproject.weatherapp.utils.permissions.PermissionCheck

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_daily,
                R.id.navigation_weekly
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val perms= intArrayOf(1,2)
        val geoLocator = GeoLocator(applicationContext, this@MainActivity)
        val bundle = Bundle()

        if (PermissionCheck.checkForPermissions(this,perms)) {
            bundle.putString("lat",geoLocator.lattitude.toString())
            bundle.putString("lon",geoLocator.longitude.toString())
            navController.navigate(R.id.navigation_daily,bundle)
        }
        else
            Log.d("Weather","Permission Denied")




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

package com.mvproject.weatherapp.utils.permissions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mvproject.weatherapp.utils.permissions.Constants.PERMISSIONS


@SuppressLint("Registered")
object PermissionCheck : AppCompatActivity() {
    fun checkForPermissions(activity: Activity?, permsArray: IntArray): Boolean {
        val permissionslist: MutableList<String> = ArrayList()
        for (i in permsArray) {
            if (ContextCompat.checkSelfPermission(activity!!, PERMISSIONS[i - 1])!= PackageManager.PERMISSION_GRANTED)
                permissionslist.add(PERMISSIONS[i - 1])
        }
        if (permissionslist.isNotEmpty()) {
            ActivityCompat.requestPermissions(activity!!, permissionslist.toTypedArray(), 101)
            return false
        }
        return true
    }
}
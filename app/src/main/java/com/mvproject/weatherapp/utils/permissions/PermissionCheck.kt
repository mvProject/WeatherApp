package com.mvproject.weatherapp.utils.permissions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mvproject.weatherapp.utils.permissions.Constants.PERMISSIONS


@SuppressLint("Registered")
// todo: for what purposes object extend Activity?? Do not needed
// todo: better to put a function into some BaseActivity: Activity or make it extension
object PermissionCheck : AppCompatActivity() {
    fun checkForPermissions(activity: Activity?, permsArray: IntArray): Boolean {
        // todo: bad naming, permissionsList :)
        val permissionslist: MutableList<String> = ArrayList()
        for (i in permsArray) {
            // todo: bad when function depends on some complex height level vars
            if (ContextCompat.checkSelfPermission(activity!!, PERMISSIONS[i - 1])!= PackageManager.PERMISSION_GRANTED)
                permissionslist.add(PERMISSIONS[i - 1])
        }
        if (permissionslist.isNotEmpty()) {
            // todo: how you will continue app work after permissions dialog will end
            ActivityCompat.requestPermissions(activity!!, permissionslist.toTypedArray(), 101) // todo: hardcoded code
            return false
        }
        return true
    }
}
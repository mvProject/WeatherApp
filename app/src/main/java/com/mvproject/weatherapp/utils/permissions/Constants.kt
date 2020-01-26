package com.mvproject.weatherapp.utils.permissions

import android.Manifest.permission.*
object Constants {
    var PERMISSIONS = arrayOf(
        ACCESS_FINE_LOCATION,
        ACCESS_COARSE_LOCATION,
        READ_CALENDAR,
        WRITE_CALENDAR,
        READ_CALL_LOG,
        WRITE_CALL_LOG,
        CAMERA,
        READ_CONTACTS,
        WRITE_CONTACTS,
        GET_ACCOUNTS,
        RECORD_AUDIO,
        READ_PHONE_STATE,
        CALL_PHONE,
        ADD_VOICEMAIL,
        USE_SIP,
        BODY_SENSORS,
        SEND_SMS,
        RECEIVE_SMS,
        READ_SMS,
        RECEIVE_WAP_PUSH,
        RECEIVE_MMS,
        READ_EXTERNAL_STORAGE,
        WRITE_EXTERNAL_STORAGE
    )
}
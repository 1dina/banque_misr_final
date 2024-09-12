package com.example.speedotransfer.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "speedotransfer_prefs"
        private const val KEY_FIRST_TIME_LAUNCH = "isFirstTimeLaunch"
    }

    fun isFirstTimeLaunch(): Boolean {
        return prefs.getBoolean(KEY_FIRST_TIME_LAUNCH, true)
    }

    fun setFirstTimeLaunch(isFirstTime: Boolean) {
        prefs.edit().putBoolean(KEY_FIRST_TIME_LAUNCH, isFirstTime).apply()
    }
}

package com.example.cravio.utils

import android.content.Context

object SharedPreferencesUtil {
    private const val PREF_NAME = "onboarding"
    private const val KEY_FIRST_LAUNCH = "isFirstLaunch"
    private const val KEY_REGISTERED = "isRegistered"

    fun isFirstLaunch(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_FIRST_LAUNCH, true)
    }

    fun setFirstLaunch(context: Context, isFirst: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean(KEY_FIRST_LAUNCH, isFirst).apply()
    }

    fun isRegistered(context: Context): Boolean {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPref.getBoolean(KEY_REGISTERED, false)
    }

    fun setRegistered(context: Context, registered: Boolean) {
        val sharedPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        sharedPref.edit().putBoolean(KEY_REGISTERED, registered).apply()
    }
} 
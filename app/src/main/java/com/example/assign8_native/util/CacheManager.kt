package com.example.assign8.util

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class CacheManager(context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("weather_cache", Context.MODE_PRIVATE)

    fun saveWeather(json: String) {
        prefs.edit { putString("cached_weather", json) }
    }

    fun getCachedWeather(): String? {
        return prefs.getString("cached_weather", null)
    }
}
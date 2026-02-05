package com.example.assign8.data.remote

import com.example.assign8.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("v1/forecast")
    suspend fun getWeather(
        @Query("latitude") lat: Double,
        @Query("longitude") lon: Double,
        @Query("current_weather") current: Boolean = true,
        @Query("daily") daily: String = "temperature_2m_max,temperature_2m_min",
        @Query("timezone") timezone: String = "auto"
    ): WeatherResponse
}
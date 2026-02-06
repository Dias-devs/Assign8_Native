package com.example.assign8_native.data.repo

import com.example.assign8.data.model.WeatherResponse
import com.example.assign8.data.remote.WeatherAPI

class WeatherRepo(private val api: WeatherAPI) {
    suspend fun fetchWeather(lat: Double, lon:  Double): WeatherResponse {
        return api.getWeather(lat, lon)
    }
}
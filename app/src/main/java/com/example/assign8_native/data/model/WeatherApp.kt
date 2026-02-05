package com.example.assign8.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val latitude: Double,
    val longitude: Double,
    @SerialName("current_weather")
    val currentWeather: CurrentWeather,
    val daily: DailyWeather
)

@Serializable
data class CurrentWeather(
    val temperature: Double,
    val windspeed: Double,
    val weathercode: Int
)

@Serializable
data class DailyWeather(
    val time: List<String>,
    @SerialName("temperature_2m_max")
    val maxTemp: List<Double>,
    @SerialName("temperature_2m_min")
    val minTemp: List<Double>
)
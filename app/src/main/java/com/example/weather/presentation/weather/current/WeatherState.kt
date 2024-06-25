package com.example.weather.presentation.weather.current

sealed class WeatherState {
    data class Success(
        val unixDate: Long = 0,
        val city: String = "",
        val country: String = "",
        val temperature: Double = 0.0,
        val st: Double = 0.0,
        val icon: String = "",
        val description: String = ""
    ) : WeatherState()
    data class Error(
        val message: String = ""
    ) : WeatherState()
    data object Empty : WeatherState()
    data object Loading : WeatherState()
}
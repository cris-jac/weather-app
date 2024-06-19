package com.example.weather.presentation.weather

sealed class WeatherState {
    data class Success(
        val city: String = "",
        val temperature: Double = 0.0,
        val description: String = "",
        val st: Double = 0.0
    ) : WeatherState()
    data class Error(
        val message: String = ""
    ) : WeatherState()
    data object Empty : WeatherState()
    data object Loading : WeatherState()
}
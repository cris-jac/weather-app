package com.example.weather.presentation.weather.forecast

import com.example.weather.repository.models.ForecastWeatherModel

sealed class WeatherForecastState {
    data class Success(
        val weatherForecast: ForecastWeatherModel
    ): WeatherForecastState()
    data class Error(
        val message: String = ""
    ): WeatherForecastState()
    data object Empty: WeatherForecastState()
    data object Loading: WeatherForecastState()
}
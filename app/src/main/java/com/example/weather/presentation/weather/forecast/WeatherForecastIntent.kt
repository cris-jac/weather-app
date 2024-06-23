package com.example.weather.presentation.weather.forecast

sealed class WeatherForecastIntent {
    data object GetForecast: WeatherForecastIntent()
}
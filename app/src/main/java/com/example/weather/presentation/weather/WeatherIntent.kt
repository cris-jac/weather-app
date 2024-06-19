package com.example.weather.presentation.weather

sealed class WeatherIntent {
    object DeleteAll: WeatherIntent()
    object DisplayCity: WeatherIntent()
    object DisplayError: WeatherIntent()
}
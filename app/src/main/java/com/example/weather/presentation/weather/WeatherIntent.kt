package com.example.weather.presentation.weather

sealed class WeatherIntent {
    object DeleteAll: WeatherIntent()
    object GetCityCurrentWeather: WeatherIntent()
    object DisplayError: WeatherIntent()
}
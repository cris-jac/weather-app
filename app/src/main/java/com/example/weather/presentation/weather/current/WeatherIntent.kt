package com.example.weather.presentation.weather.current

sealed class WeatherIntent {
    object DeleteAll: WeatherIntent()
    object GetCityCurrentWeather: WeatherIntent()
    object DisplayError: WeatherIntent()
}
package com.example.weather.repository

import com.example.weather.repository.models.CityModel
import com.example.weather.repository.models.CurrentWeatherModel
import com.example.weather.repository.models.ForecastWeatherModel

interface Repository {
    suspend fun searchCity(city: String): List<CityModel>
    suspend fun getForecast(lat: Float, lon: Float): ForecastWeatherModel
    suspend fun getWeather(lat: Float, lon: Float): CurrentWeatherModel
}
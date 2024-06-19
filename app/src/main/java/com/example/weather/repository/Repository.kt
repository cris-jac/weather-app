package com.example.weather.repository

import com.example.weather.repository.models.CityModel
import com.example.weather.repository.models.CurrentWeatherModel
import com.example.weather.repository.models.ForecastWeatherModel

interface Repository {
    suspend fun searchCity(city: String): List<CityModel>
    suspend fun getForecast(lat: String, lon: String): ForecastWeatherModel
    suspend fun getWeather(lat: String, lon: String): CurrentWeatherModel
}
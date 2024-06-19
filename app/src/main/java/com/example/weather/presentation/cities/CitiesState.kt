package com.example.weather.presentation.cities

import com.example.weather.repository.models.CityModel

sealed class CitiesState {
    data object Empty: CitiesState()
    data object Loading: CitiesState()
    data class Result(val cities: List<CityModel>): CitiesState()
    data class Error(val message: String): CitiesState()
}
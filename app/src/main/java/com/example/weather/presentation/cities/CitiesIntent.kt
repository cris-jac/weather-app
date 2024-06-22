package com.example.weather.presentation.cities

import com.example.weather.repository.models.CityModel

sealed class CitiesIntent {
    data class Search(val name: String): CitiesIntent()
    data class Select(val city: CityModel): CitiesIntent()
}
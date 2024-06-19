package com.example.weather.presentation.cities

sealed class CitiesIntent {
    data class Search(val name: String): CitiesIntent()
    data class Select(val lat: Float, val lon: Float): CitiesIntent()
}
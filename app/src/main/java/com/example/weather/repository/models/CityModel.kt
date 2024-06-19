package com.example.weather.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class CityModel(
    val name: String,
    val lat: Double,
    val lon: Double,
    val country: String,
    val state: String,
)
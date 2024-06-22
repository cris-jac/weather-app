package com.example.weather.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class CityModel(
    val name: String,
    val lat: Float,
    val lon: Float,
    val country: String? = "",
    val state: String? = ""
)
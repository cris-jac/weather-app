package com.example.weather.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherModel(
    val coord: Coord? = null,
    val weather: List<Weather>? = null,
    val base: String? = null,
    val main: Main? = null,
    val visibility: Long? = null,
    val wind: Wind? = null,
    val rain: Rain? = null,
    val clouds: Clouds? = null,
    val dt: Long? = null,
    val sys: Sys? = null,
    val timezone: Long? = null,
    val id: Long? = null,
    val name: String? = null,
    val cod: Long? = null
)

@Serializable
data class Coord(
    val lon: Float,
    val lat: Float,
)

@Serializable
data class Weather(
    val id: Long? = null,
    val main: String? = "",
    val description: String? = "",
    val icon: String? = "",
)

@Serializable
data class Main(
    val temp: Double? = null,
    val feelsLike: Double? = null,
    val tempMin: Double? = null,
    val tempMax: Double? = null,
    val pressure: Long? = null,
    val humidity: Long? = null,
    val seaLevel: Long? = null,
    val grndLevel: Long? = null,
)

@Serializable
data class Wind(
    val speed: Double? = null,
    val deg: Long? = null,
    val gust: Double? = null,
)

@Serializable
data class Rain(
    val n1h: Double? = null,
)

@Serializable
data class Clouds(
    val all: Long? = null,
)

@Serializable
data class Sys(
    val type: Long? = null,
    val id: Long? = null,
    val country: String? = "",
    val sunrise: Long? = null,
    val sunset: Long? = null,
)

package com.example.weather.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherModel(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val rain: Rain,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timezone: Long,
    val id: Long,
    val name: String,
    val cod: Long,
)

@Serializable
data class Coord(
    val lon: Float,
    val lat: Float,
)

@Serializable
data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)

@Serializable
data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
    val pressure: Long,
    val humidity: Long,
    val seaLevel: Long,
    val grndLevel: Long,
)

@Serializable
data class Wind(
    val speed: Double,
    val deg: Long,
    val gust: Double,
)

@Serializable
data class Rain(
    val n1h: Double,
)

@Serializable
data class Clouds(
    val all: Long,
)

@Serializable
data class Sys(
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long,
)

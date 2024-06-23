package com.example.weather.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherModel(
    val latitude: Double,
    val longitude: Double,
    val generationtimeMs: Double? = null,
    val utcOffsetSeconds: Long? = null,
    val timezone: String? = "",
    val timezoneAbbreviation: String? = "",
    val elevation: Double? = null,
    val dailyUnits: DailyUnits? = null,
    val daily: Daily? = null,
)

@Serializable
data class DailyUnits(
    val time: String? = "",
    val weatherCode: String? = "",
    val temperature2mMax: String? = "",
    val temperature2mMin: String? = "",
    val precipitationProbabilityMax: String? = "",
)

@Serializable
data class Daily(
    val time: List<String>? = null,
    val weatherCode: List<Long>? = null,
    val temperature2mMax: List<Double>? = null,
    val temperature2mMin: List<Double>? = null,
    val precipitationProbabilityMax: List<Long>? = null,
)

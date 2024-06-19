package com.example.weather.repository.models

import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherModel(
    val latitude: Long,
    val longitude: Long,
    val generationtimeMs: Double,
    val utcOffsetSeconds: Long,
    val timezone: String,
    val timezoneAbbreviation: String,
    val elevation: Long,
    val dailyUnits: DailyUnits,
    val daily: Daily,
)

@Serializable
data class DailyUnits(
    val time: String,
    val weatherCode: String,
    val temperature2mMax: String,
    val temperature2mMin: String,
    val precipitationProbabilityMax: String,
)

@Serializable
data class Daily(
    val time: List<String>,
    val weatherCode: List<Long>,
    val temperature2mMax: List<Double>,
    val temperature2mMin: List<Double>,
    val precipitationProbabilityMax: List<Long>,
)

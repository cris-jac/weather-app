package com.example.weather.repository.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ForecastWeatherModel(
    val latitude: Double? = null,
    val longitude: Double? = null,
    @SerialName("generationtime_ms") val generationtimeMs: Double? = null,
    @SerialName("utc_offset_seconds") val utcOffsetSeconds: Long? = null,
    val timezone: String? = "",
    @SerialName("timezone_abbreviation") val timezoneAbbreviation: String? = "",
    val elevation: Double? = null,
    @SerialName("daily_units") val dailyUnits: DailyUnits? = null,
    val daily: Daily? = null,
)

@Serializable
data class DailyUnits(
    val time: String? = "",
    @SerialName("weather_code") val weatherCode: String? = "",
    @SerialName("temperature_2m_max") val temperature2mMax: String? = "",
    @SerialName("temperature_2m_min") val temperature2mMin: String? = "",
    @SerialName("precipitation_probability_max") val precipitationProbabilityMax: String? = "",
)

@Serializable
data class Daily(
    val time: List<String>? = null,
    @SerialName("weather_code") val weatherCode: List<Int>? = null,
    @SerialName("temperature_2m_max") val temperature2mMax: List<Float>? = null,
    @SerialName("temperature_2m_min") val temperature2mMin: List<Float>? = null,
    @SerialName("precipitation_probability_max") val precipitationProbabilityMax: List<Int>? = null,
)


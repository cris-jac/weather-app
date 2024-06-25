package com.example.weather.presentation.weather.forecast

fun WeatherCodeMapper(forecastWeatherCode: Int): String {
    return forecastWeatherCodeMapToWeatherCode[forecastWeatherCode] ?: throw IllegalArgumentException("Invalid forecast weather code")
}

/*
Forecast weather values and description from: https://open-meteo.com/en/docs#hourly=&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max
 */
val forecastWeatherCodeMapToWeatherCode = mapOf(
    0 to "01d",
    1 to "02d",
    2 to "02d",
    3 to "02d",
    45 to "50d",
    48 to "50d",
    51 to "09d",
    53 to "09d",
    55 to "09d",
    56 to "09d",
    57 to "09d",
    61 to "10d",
    63 to "10d",
    65 to "10d",
    66 to "13d",
    67 to "13d",
    71 to "13d",
    73 to "13d",
    75 to "13d",
    77 to "13d",
    80 to "09d",
    81 to "09d",
    82 to "09d",
    85 to "13d",
    86 to "13d",
    95 to "11d",
    99 to "11d"
)
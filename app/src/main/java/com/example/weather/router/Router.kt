package com.example.weather.router

interface Router {
    fun navigate(route: Route)
}

sealed class Route(val id: String) {
    data object Cities: Route("cities")
    data class Weather(val lat: Float, val lon: Float): Route("weather")
}
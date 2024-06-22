package com.example.weather.router

interface IRouter {
    fun navigate(route: String)
}

sealed class Route(val id: String) {
    data object Cities: Route("cities")
    data class Weather(val lat: Float, val lon: Float): Route("weather")
}
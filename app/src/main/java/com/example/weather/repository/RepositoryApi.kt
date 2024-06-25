package com.example.weather.repository

import com.example.weather.repository.models.CityModel
import com.example.weather.repository.models.CurrentWeatherModel
import com.example.weather.repository.models.ForecastWeatherModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RepositoryApi: Repository {
    // open weather map -> current
    private val owmKey = "175f9f25c32ddb543824968a81c7b353"
    private val owmBaseUrl = "api.openweathermap.org"

    // open meteo -> forecast
    private val omBaseUrl = "api.open-meteo.com"

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun searchCity(city: String): List<CityModel> {
        val limit = 5
        val response = client.get("http://${owmBaseUrl}/geo/1.0/direct?q=${city}&limit=${limit}&appid=${owmKey}")

        if (response.status == HttpStatusCode.OK) {
            val cities = response.body<List<CityModel>>()
            return cities
        } else {
            throw Exception()
        }
    }

    override suspend fun getWeather(lat: Float, lon: Float): CurrentWeatherModel {
        val latitude = lat.toString()
        val longitude = lon.toString()
        val response = client.get("https://${owmBaseUrl}/data/2.5/weather?lat=${latitude}&lon=${longitude}&appid=${owmKey}")

        if (response.status == HttpStatusCode.OK) {
            val currentWeather = response.body<CurrentWeatherModel>()
            return currentWeather
        } else {
            throw Exception()
        }
    }

    override suspend fun getForecast(lat: Float, lon: Float): ForecastWeatherModel {
        val latitude = "%.2f".format(lat)
        val longitude = "%.2f".format(lon)
        val response = client.get("https://${omBaseUrl}/v1/forecast?latitude=${latitude}&longitude=${longitude}&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max&timezone=auto")

        if (response.status == HttpStatusCode.OK) {
//            val forecastWeather = response.body<ForecastWeatherModel>()
            val forecastWeatherResponse = response.bodyAsText()
            println(forecastWeatherResponse)

            val forecastWeather = Json.decodeFromString<ForecastWeatherModel>(forecastWeatherResponse)
            return forecastWeather
        } else {
            throw Exception()
        }
    }
}
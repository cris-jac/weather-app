package com.example.weather.repository

import com.example.weather.repository.models.CityModel
import com.example.weather.repository.models.CurrentWeatherModel
import com.example.weather.repository.models.ForecastWeatherModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RepositoryApi: Repository {
    // open weather map -> current
    private val owmKey = "175f9f25c32ddb543824968a81c7b353"
    private val owmBaseUrl = "http://api.openweathermap.org"

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
        val response = client.get("http://${owmBaseUrl}/geo/1.0/direct?q=${city}&limit=${limit}&appid=${owmKey}") {

        }

        if (response.status == HttpStatusCode.OK) {
            val cities = response.body<List<CityModel>>()
            return cities
        } else {
            throw Exception()
        }
    }

    override suspend fun getWeather(lat: String, lon: String): CurrentWeatherModel {
        val response = client.get("https://${owmBaseUrl}/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${owmKey}") {

        }

        if (response.status == HttpStatusCode.OK) {
            val currentWeather = response.body<CurrentWeatherModel>()
            return currentWeather
        } else {
            throw Exception()
        }
    }

    override suspend fun getForecast(lat: String, lon: String): ForecastWeatherModel {
        val response = client.get("https://${omBaseUrl}/v1/forecast?latitude=${lat}&longitude=${lon}&daily=weather_code,temperature_2m_max,temperature_2m_min,precipitation_probability_max&timezone=auto") {

        }

        if (response.status == HttpStatusCode.OK) {
            val cities = response.body<ForecastWeatherModel>()
            return cities
        } else {
            throw Exception()
        }
    }
}
package com.example.weather.presentation.weather.forecast

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.Repository
import com.example.weather.router.IRouter
import kotlinx.coroutines.launch

class WeatherForecastViewModel(
    val repository: Repository,
    val router: IRouter,
    val lat: Float,
    val lon: Float
): ViewModel() {
    var uiState by mutableStateOf<WeatherForecastState>(WeatherForecastState.Empty)

    fun execute(intent: WeatherForecastIntent) {
        when(intent) {
            WeatherForecastIntent.GetForecast -> getForecast()
        }
    }

    private fun getForecast() {
        uiState = WeatherForecastState.Loading

        viewModelScope.launch {
            try {
                val forecast = repository.getForecast(lat = lat, lon = lon)
                uiState = WeatherForecastState.Success(forecast)
            } catch (exception: Exception) {
                uiState = WeatherForecastState.Error("Error: ${exception.message}")
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class WeatherForecastViewModelFactory(
    private val repository: Repository,
    private val router: IRouter,
    val lat: Float,
    val lon: Float
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherForecastViewModel::class.java)) {
            return WeatherForecastViewModel(repository, router, lat, lon) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
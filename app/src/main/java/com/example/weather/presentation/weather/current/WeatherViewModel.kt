package com.example.weather.presentation.weather.current

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.Repository
import com.example.weather.router.IRouter
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WeatherViewModel(
    val repository: Repository,
    val router: IRouter,
    val lat: Float,
    val lon: Float
): ViewModel() {
    var uiState by mutableStateOf<WeatherState>(WeatherState.Empty)

    fun execute(intent: WeatherIntent){
        when(intent) {
            WeatherIntent.DeleteAll -> deleteAll()
            WeatherIntent.GetCityCurrentWeather -> getCityCurrentWeather()
            WeatherIntent.DisplayError -> displayError()
        }
    }

    private fun displayError(){
        uiState = WeatherState.Error("Error")
    }

    private fun deleteAll(){
        uiState = WeatherState.Empty
    }

    private fun getCityCurrentWeather(){
        uiState = WeatherState.Loading
        viewModelScope.launch {
            try {
                val currentWeather = repository.getWeather(lat, lon)
                uiState = WeatherState.Success(
                    city = currentWeather.name ?: "",
                    temperature = currentWeather.main?.temp ?: 0.0,
                    description = currentWeather.weather?.first()?.description ?: "",
                    st = currentWeather.main?.feelsLike ?: 0.0
                )
            } catch (exception: Exception) {
                uiState = WeatherState.Error("Error: ${exception.message}")
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
    private val repository: Repository,
    private val router: IRouter,
    private val lat: Float,
    private val lon: Float
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository, router, lat, lon) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
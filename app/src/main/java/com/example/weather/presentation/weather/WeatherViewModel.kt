package com.example.weather.presentation.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.Repository
import com.example.weather.repository.models.CityModel
import com.example.weather.router.Router
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class WeatherViewModel(
    val repository: Repository,
    val router: Router
): ViewModel() {
    var uiState by mutableStateOf<WeatherState>(WeatherState.Empty)

    fun execute(intent: WeatherIntent){
        when(intent) {
            WeatherIntent.DeleteAll -> deleteAll()
            WeatherIntent.DisplayCity -> displayCity()
            WeatherIntent.DisplayError -> displayError()
        }
    }

    private fun displayError(){
        uiState = WeatherState.Error("Error")
    }

    private fun deleteAll(){
        uiState = WeatherState.Empty
    }

    private fun displayCity(){
        WeatherState.Loading
        viewModelScope.launch {
            try {
                val currentWeather = repository.getWeather("-34", "-45")
                WeatherState.Success(
                    city = currentWeather.name,
                    temperature = currentWeather.main.temp,
                    description = currentWeather.weather.first().toString(),
                    st = currentWeather.main.feelsLike
                )
            } catch (exception: Exception) {
                WeatherState.Error("error msg")
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class WeatherViewModelFactory(
    private val repository: Repository,
    private val router: Router
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            return WeatherViewModel(repository, router) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
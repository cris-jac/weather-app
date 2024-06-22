package com.example.weather.presentation.cities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.Repository
import com.example.weather.repository.models.CityModel
import com.example.weather.router.IRouter
import com.example.weather.router.Route
import com.example.weather.router.Router
import kotlinx.coroutines.launch

class CitiesViewModel(
    val repository: Repository,
    val router: IRouter
): ViewModel() {
    var uiState by mutableStateOf<CitiesState>(CitiesState.Empty)

    fun execute(intent: CitiesIntent) {
        when(intent) {
            is CitiesIntent.Search -> search(name = intent.name)
            is CitiesIntent.Select -> select(city = intent.city)
        }
    }

    private fun search(name: String) {
        uiState = CitiesState.Loading

        viewModelScope.launch {
            try {
                val citiesList = repository.searchCity(name)
                uiState = CitiesState.Result(citiesList)

            } catch (exception: Exception) {
                println(exception)
                uiState = CitiesState.Error("Error: ${exception.toString()}")
            }
        }
    }

    private fun select(city: CityModel) {
        val lat = city.lat.toString()
        val lon = city.lon.toString()

        val route = "weather?lat=${lat}&lon=${lon}&name=${city.name}"
        uiState = CitiesState.Empty
        router.navigate(route)
    }
}

@Suppress("UNCHECKED_CAST")
class CitiesViewModelFactory(
    private val repository: Repository,
    private val router: Router
): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CitiesViewModel::class.java)) {
            return CitiesViewModel(repository, router) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
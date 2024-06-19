package com.example.weather.presentation.cities

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weather.repository.Repository
import com.example.weather.router.Route
import com.example.weather.router.Router
import kotlinx.coroutines.launch

class CitiesViewModel(
    val repository: Repository,
    val router: Router
): ViewModel() {
    var uiState by mutableStateOf<CitiesState>(CitiesState.Empty)

    fun execute(intent: CitiesIntent) {
        when(intent) {
            is CitiesIntent.Search -> search(name = intent.name)
            is CitiesIntent.Select -> select()
        }
    }

    private fun search(name: String) {
        uiState = CitiesState.Loading

        viewModelScope.launch {
            try {
                val citiesList = repository.searchCity(name)
                uiState = CitiesState.Result(citiesList)

            } catch (exception: Exception) {
                uiState = CitiesState.Error("Error")
            }
        }
    }

    private fun select() {
        uiState = CitiesState.Empty
        router.navigate(Route.Weather(0.0f, 0.0f))
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
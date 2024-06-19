package com.example.weather.presentation.weather

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
//import com.example.weather.UiState
import com.example.weather.repository.RepositoryApi
import com.example.weather.router.Enrutador

@Composable
fun WeatherPage(
    navHostController: NavHostController,
    lat: Float,
    lon: Float
) {
    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = RepositoryApi(),
            router = Enrutador(navHostController)
        )
    )

    WeatherView(
        state = viewModel.uiState
    )
}
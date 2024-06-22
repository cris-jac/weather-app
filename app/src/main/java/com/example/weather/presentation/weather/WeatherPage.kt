package com.example.weather.presentation.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
//import com.example.weather.UiState
import com.example.weather.repository.RepositoryApi
import com.example.weather.router.Router

@Composable
fun WeatherPage(
    navHostController: NavHostController,
    lat: Float = 77.77f,
    lon: Float = 66.66f,
    name: String = "Joao"
) {
//    Column {
//        Text(text = "Lat: $lat")
//        Text(text = "Name: $name")
//        Text(text = lon.toString())
//    }

    val viewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = RepositoryApi(),
            router = Router(navHostController),
            lat = lat,
            lon = lon
        )
    )

    WeatherView(
        state = viewModel.uiState,
        onAction = { intent -> viewModel.execute(intent) }
    )
}
package com.example.weather.presentation.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weather.presentation.weather.current.WeatherView
import com.example.weather.presentation.weather.current.WeatherViewModel
import com.example.weather.presentation.weather.current.WeatherViewModelFactory
import com.example.weather.presentation.weather.forecast.WeatherForecastView
import com.example.weather.presentation.weather.forecast.WeatherForecastViewModel
import com.example.weather.presentation.weather.forecast.WeatherForecastViewModelFactory
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

    val currentViewModel: WeatherViewModel = viewModel(
        factory = WeatherViewModelFactory(
            repository = RepositoryApi(),
            router = Router(navHostController),
            lat = lat,
            lon = lon
        )
    )

    val forecastViewModel: WeatherForecastViewModel = viewModel(
        factory = WeatherForecastViewModelFactory(
            repository = RepositoryApi(),
            router = Router(navHostController),
            lat = lat,
            lon = lon
        )
    )

    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.LightGray)
                .weight(1f)
        ) {
            Text(text = "Current Weather")
            WeatherView(
                state = currentViewModel.uiState,
                onAction = { intent -> currentViewModel.execute(intent) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(Color.Cyan)
                .weight(1f)
        ) {
            Text(text = "Forecast Weather")
            WeatherForecastView(
                state = forecastViewModel.uiState,
                onAction = { intent -> forecastViewModel.execute(intent) }
            )
        }
    }
}
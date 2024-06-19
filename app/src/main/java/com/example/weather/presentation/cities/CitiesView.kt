package com.example.weather.presentation.cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weather.presentation.weather.WeatherState
import com.example.weather.presentation.weather.WeatherView
import com.example.weather.repository.models.CityModel
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun CitiesView(
    modifier: Modifier = Modifier,
    state: CitiesState,
    onAction: (CitiesIntent) -> Unit
) {
//    var latValue by remember{ mutableStateOf(uiState.latitud) }
//    var lonValue by remember{ mutableStateOf(uiState.longitud) }
    var lat by remember { mutableStateOf("") }
    var lon by remember { mutableStateOf("") }

//    Column(modifier = modifier.fillMaxSize()) {
//        TextField(
//            value = "",
//            label = { Text(text = "Latitud") },
//            onValueChange = {  }
//        )
//        TextField(
//            value = "",
//            label = { Text(text = "Longitud") },
//            onValueChange = { }
//        )
//        Button(onClick = {
//            navController.navigate("weather")
//        }) {
//            Text(text = "Get weather")
//        }
//    }

    Column(modifier = modifier) {
        TextField(
            value = lat,
            label = { Text(text = "Latitud") },
            onValueChange = { lat = it }
        )
        TextField(
            value = lon,
            label = { Text(text = "Longitud") },
            onValueChange = { lon = it }
        )
        when(state) {
            CitiesState.Empty -> Text(text = "Empty")
            is CitiesState.Error -> Text(text = state.message)
            CitiesState.Loading -> Text(text = "Loading...")
            is CitiesState.Result -> ListOfCities(cities = state.cities) { lat, lon ->
                onAction(CitiesIntent.Select(lat.toFloat(), lon.toFloat()))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfCities(
    cities: List<CityModel>,
    onSelect: (Float, Float) -> Unit
) {
    LazyColumn {
        items(items = cities) {city ->
            Card(onClick = { onSelect(city.lat.toFloat(), city.lon.toFloat()) }) {
                Text(text = city.name)
            }
        }
    }
}

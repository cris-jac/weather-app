package com.example.weather.presentation.cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
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

    var name by remember { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = name,
            label = { Text(text = "Name") },
            onValueChange = {
                if (it.isNotBlank()) {
                    name = it
                    onAction(CitiesIntent.Search(name))
                }
            }
        )

        when(state) {
            CitiesState.Empty -> Text(text = "Empty")
            is CitiesState.Error -> Text(text = state.message)
            CitiesState.Loading -> Text(text = "Loading...")
            is CitiesState.Result -> ListOfCities(cities = state.cities, { city -> onAction(CitiesIntent.Select(city)) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfCities(
    cities: List<CityModel>
    , onSelect: (CityModel) -> Unit
) {
    LazyColumn {
        items(items = cities) {city ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(2.dp),
                onClick = { onSelect(city) }
            ) {
                Text(text = "${city.name}, ${city.country}")
            }
        }
    }
}

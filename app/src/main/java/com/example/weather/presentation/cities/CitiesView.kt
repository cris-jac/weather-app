package com.example.weather.presentation.cities

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weather.R
import com.example.weather.repository.models.CityModel

@Composable
fun CitiesView(
    modifier: Modifier = Modifier,
    state: CitiesState,
    onAction: (CitiesIntent) -> Unit
) {

    var name by remember { mutableStateOf("") }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = name,
            label = { Text(text = "Ciudad") },
            onValueChange = {
                if (it.isNotBlank()) {
                    name = it
                    onAction(CitiesIntent.Search(name))
                }
            },
            shape = RoundedCornerShape(32.dp)
        )

        when(state) {
            CitiesState.Empty -> Text(text = "")
            is CitiesState.Error -> Text(text = state.message)
            CitiesState.Loading -> Text(text = "Loading...")
            is CitiesState.Result -> ListOfCities(cities = state.cities, { city -> onAction(CitiesIntent.Select(city)) })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListOfCities(
    cities: List<CityModel>,
    onSelect: (CityModel) -> Unit
) {
    LazyColumn {
        items(items = cities) {city ->
            Card(
                modifier = Modifier
                    .padding(1.dp)
                    .fillMaxWidth(),
                onClick = { onSelect(city) }
            ) {
                Text(
                    text = "${city.name}, ${city.country}",
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}
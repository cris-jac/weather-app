package com.example.weather.presentation.weather.forecast

import android.util.Log
import androidx.collection.emptyFloatList
import androidx.collection.emptyLongList
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.weather.repository.models.ForecastWeatherModel

@Composable
fun WeatherForecastView(
    modifier: Modifier = Modifier,
    state: WeatherForecastState,
    onAction: (WeatherForecastIntent) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(WeatherForecastIntent.GetForecast)
    }
//    onAction(WeatherForecastIntent.GetForecast)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Forecast - View")

        when(state) {
            WeatherForecastState.Empty -> {
                Log.d("WeatherForecastView", "State: Empty")
                EmptyView()
            }
            is WeatherForecastState.Error -> {
                Log.d("WeatherForecastView", "State: Error")
                ErrorView(message = state.message)
            }
            WeatherForecastState.Loading -> {
                Log.d("WeatherForecastView", "State: Loading")
                LoadingView()
            }
            is WeatherForecastState.Success -> {
                Log.d("WeatherForecastView", "State: Success")
                WeatherForecastResultView(forecast = state.weatherForecast)
            }
        }
    }

}

@Composable
fun EmptyView() {
    Text(text = "Empty forecast")
}

@Composable
fun LoadingView() {
    Text(text = "Loading...")
}

@Composable
fun ErrorView(message: String) {
    Text(text = message)
}

@Composable
fun WeatherForecastResultView(forecast: ForecastWeatherModel) {
    val daily = forecast.daily ?: return Text(text = "ResultView not available")

    val times = daily.time ?: emptyList()
    val weatherCodes: List<Long> = daily.weatherCode?.toList() ?: emptyList()
    val maxTemps: List<Double> = daily.temperature2mMax ?: emptyList()
    val minTemps: List<Double> = daily.temperature2mMin ?: emptyList()
    val maxPrcpProbs: List<Long> = daily.precipitationProbabilityMax ?: emptyList()
    
    Text(text = forecast.toString())
    
    Text(text = "List of forecast - Result View")
    
    LazyColumn {
        itemsIndexed(times) { index, time ->
            WeatherForecastCard(
                time = time,
                weatherCode = weatherCodes.getOrNull(index),
                tempMax = maxTemps.getOrNull(index),
                tempMin = minTemps.getOrNull(index),
                precipitationProbability = maxPrcpProbs.getOrNull(index)
            )
        }
    }
}

@Composable
fun WeatherForecastCard(
    time: String,
    weatherCode: Long?,
    tempMax: Double?,
    tempMin: Double?,
    precipitationProbability: Long?
) {
    Card(modifier = Modifier.padding(8.dp)) {
        Text(text = "Fecha: $time")
        Text(text = "Codigo: $weatherCode")
        Text(text = "Temperatura maxima: $tempMax °c")
        Text(text = "Temperatura minima: $tempMin °c")
        Text(text = "Probabilidad de lluvia: $precipitationProbability %")
    }
}

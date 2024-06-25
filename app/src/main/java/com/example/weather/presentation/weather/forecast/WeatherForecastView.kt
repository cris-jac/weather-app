package com.example.weather.presentation.weather.forecast

import android.annotation.SuppressLint
import android.util.Log
import androidx.collection.emptyFloatList
import androidx.collection.emptyLongList
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.AsyncImage
import com.example.weather.repository.models.ForecastWeatherModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

@Composable
fun WeatherForecastView(
    modifier: Modifier = Modifier,
    state: WeatherForecastState,
    onAction: (WeatherForecastIntent) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(WeatherForecastIntent.GetForecast)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
    val weatherCodes: List<Int> = daily.weatherCode ?: emptyList()
    val maxTemps: List<Float> = daily.temperature2mMax ?: emptyList()
    val minTemps: List<Float> = daily.temperature2mMin ?: emptyList()
    val maxPrcpProbs: List<Int> = daily.precipitationProbabilityMax ?: emptyList()
    
//    Text(text = forecast.toString())
    
//    Text(text = "List of forecast - Result View")

    Text(
        modifier = Modifier.padding(bottom = 4.dp),
        text = "Pronostico para esta semana",
        fontSize = 20.sp,
        color = Color.White
    )

    LazyRow (
    ) {
        itemsIndexed(times) { index, time ->
            if (index > 0) {
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
}

@Composable
fun WeatherForecastCard(
    time: String,
    weatherCode: Int?,
    tempMax: Float?,
    tempMin: Float?,
    precipitationProbability: Int?
) {

    val dayName = getDayName(time) ?: ""

    Card(
        modifier = Modifier
            .padding(8.dp)
            .width(128.dp)
    ) {
        Column (
            modifier = Modifier
                .background(color = Color.Cyan.copy(0.2f))
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Text(text = time)
            Text(text = dayName, fontSize = 18.sp)
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .fillMaxWidth()
            ) {
//                Text(text = "Codigo: ${WeatherCodeMapper(weatherCode ?: 0)}")
                AsyncImage(
                    model = "https://openweathermap.org/img/wn/${WeatherCodeMapper(weatherCode ?: 0)}@4x.png",
                    contentDescription = "WeatherCode: $weatherCode",
                    modifier = Modifier.fillMaxSize()
                )
            }
//        Text(text = "Temperatura maxima: $tempMax °c")
//        Text(text = "Temperatura minima: $tempMin °c")
//        Text(text = "Probabilidad de lluvia: $precipitationProbability %")
            StyledData(key = "T. maxima", value = "$tempMax °c")
            StyledData(key = "T. minima", value = "$tempMin °c")
            StyledData(key = "Prob. lluvia", value = "$precipitationProbability %")
        }
    }
}

@Composable
fun StyledData(key: String, value: String) {
    Column (modifier = Modifier.padding(vertical = 4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = key, fontSize = 12.sp)
        Text(text = value, fontSize = 20.sp)
    }
}

@SuppressLint("SimpleDateFormat")
fun getDayName(stringDate: String): String {
    val format = SimpleDateFormat("yyyy-MM-dd")
    format.timeZone = TimeZone.getTimeZone("UTC")

    val date: Date = format.parse(stringDate)

    val calendar = Calendar.getInstance()
    calendar.timeZone = TimeZone.getTimeZone("UTC")
    calendar.time = date

    val days: List<String> = listOf("Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado")
    val dayIndex = calendar.get(Calendar.DAY_OF_WEEK) - 1
    val day = days[dayIndex]
    return day
}
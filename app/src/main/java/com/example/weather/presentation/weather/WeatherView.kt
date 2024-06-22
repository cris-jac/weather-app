package com.example.weather.presentation.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.weather.ui.theme.WeatherTheme

@Composable
fun WeatherView(
    modifier: Modifier = Modifier,
    state: WeatherState,
    onAction: (WeatherIntent) -> Unit
) {
    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onAction(WeatherIntent.GetCityCurrentWeather)
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state) {
            WeatherState.Empty -> EmptyView()
            is WeatherState.Error -> ErrorView(message = state.message)
            WeatherState.Loading -> EmptyView()
            is WeatherState.Success -> CurrentView(
                city = state.city,
                temperature = state.temperature,
                description = state.description,
                st = state.st
            )
        }    
    }

}

@Composable
fun EmptyView(){
    Text(text = "Empty")
}

@Composable
fun ErrorView(message: String){
    Text(text = message)
}

@Composable
fun CurrentView(city: String, temperature: Double, description: String, st: Double){
    Column {
        Text(text = city)
        Text(text = "${String.format("%.1f",temperature.minus(273.15))} °C")
        Text(text = description)
        Text(text = "Sens. term.: $st")
    }
}


@Preview(showBackground = true)
@Composable
fun EmptyViewPreview(){
    WeatherTheme {
        WeatherView(state = WeatherState.Empty, onAction = {})
    }
}



//@Suppress("UNUSED_EXPRESSION")
//suspend fun getWeather(lat: String, lon: String): String? {
//    val client = HttpClient() {
//        install(ContentNegotiation) {
//            json(Json {
//                ignoreUnknownKeys = true
//            })
//        }
//    }
//
//    val baseUrl = "https://api.openweathermap.org"
//    val key = "175f9f25c32ddb543824968a81c7b353"
//    val response = client.get("${baseUrl}/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${key}")
//
//    if (response.status == HttpStatusCode.OK) {
//        println("Status OK")
//        val weatherResponse = response.body<WeatherModel>()
//        return weatherResponse.toString()
//    } else {
//        return "Error fetching weather"
//    }
//}
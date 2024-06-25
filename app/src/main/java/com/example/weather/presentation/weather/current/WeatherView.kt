package com.example.weather.presentation.weather.current

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import com.example.weather.ui.theme.WeatherTheme
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.util.Date

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
            .padding(20.dp)
            .background(color = Color.LightGray.copy(alpha = 0.1f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when(state) {
            WeatherState.Empty -> EmptyView()
            is WeatherState.Error -> ErrorView(message = state.message)
            WeatherState.Loading -> EmptyView()
            is WeatherState.Success -> CurrentView(
                unixDate = state.unixDate,
                city = state.city,
                country = state.country,
                temperature = state.temperature,
                st = state.st,
                icon = state.icon,
                description = state.description
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
fun CurrentView(unixDate: Long, city: String, country: String, temperature: Double, st: Double, icon: String, description: String){
    Column(
        modifier = Modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = SimpleDateFormat("dd-MM-yyyy").format(Date(unixDate * 1000)))
        Text(text = "$city, $country", fontSize = 24.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = "${String.format("%.1f", temperature.minus(273.15))} °C", fontSize = 64.sp)
        Box (modifier = Modifier.size(192.dp)) {
            AsyncImage(
                model = "https://openweathermap.org/img/wn/${icon}@4x.png",
                contentDescription = description,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (st != 0.0) { Text(text = "Sensacion termica: ${String.format("%.1f", st)} °C") }
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
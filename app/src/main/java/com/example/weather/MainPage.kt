package com.example.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.weather.presentation.cities.CitiesPage
import com.example.weather.presentation.weather.WeatherPage
import com.example.weather.router.Route
import com.example.weather.router.Route.Weather

@Composable
fun MainPage() {
    val navHostController = rememberNavController()
//    val uiState by rememberSaveable {
//        mutableStateOf(UiState("", ""))
//    }

    NavHost(
        navController = navHostController,
        startDestination = Route.Cities.id
    ) {
        composable(route = Route.Cities.id){
            CitiesPage(navHostController)
        }

        composable(
            route = Weather(0.0f,0.0f).id,
            arguments = listOf(
                navArgument("lat") { NavType.FloatType },
                navArgument("lon") { NavType.FloatType }
            )

        ) {
            val lat = it.arguments?.getFloat("lat") ?: 0.0f
            val lon = it.arguments?.getFloat("lon") ?: 0.0f
            WeatherPage(navHostController, lat = lat, lon = lon)
        }
    }

}

//data class UiState(var latitud: String = "", var longitud: String = "")

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NavigationTheme {
//        Greeting("Android")
//    }
//}
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
        startDestination = "cities"
    ) {
        composable(route = "cities"){
            CitiesPage(navHostController)
        }

        composable(
            route = "weather?lat={lat}&lon={lon}&name={name}",
            arguments = listOf(
                navArgument("lat") { NavType.StringType },
                navArgument("lon") { NavType.StringType },
                navArgument("name") { NavType.StringType }
            )

        ) {
            val lat = it.arguments?.getString("lat")?.toFloatOrNull() ?: 32.0f
            val lon = it.arguments?.getString("lon")?.toFloatOrNull() ?: 32.0f
            val name = it.arguments?.getString("name") ?: "pepe"
            WeatherPage(navHostController, lat = lat, lon = lon, name = name)
        }
    }

}

package com.example.weather.router

import androidx.navigation.NavHostController

class Enrutador(
    val navHostController: NavHostController
): Router {
    override fun navigate(route: Route) {
        navHostController.navigate(route.id)
    }

}
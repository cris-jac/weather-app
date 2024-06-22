package com.example.weather.router

import androidx.navigation.NavHostController

class Router(
    val navHostController: NavHostController
): IRouter {
    override fun navigate(route: String) {
        navHostController.navigate(route)
    }

}
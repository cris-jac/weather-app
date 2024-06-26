package com.example.weather.presentation.cities

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.weather.presentation.weather.AppBackground
//import com.example.weather.UiState
import com.example.weather.repository.RepositoryApi
import com.example.weather.router.Router

@Composable
fun CitiesPage(
    navHostController: NavHostController
) {
    val viewModel: CitiesViewModel = viewModel(
        factory = CitiesViewModelFactory(
            repository = RepositoryApi(),
            router = Router(navHostController)
        )
    )
    AppBackground()
    CitiesView(
        state = viewModel.uiState,
        onAction = { intent -> viewModel.execute(intent) }
    )
}
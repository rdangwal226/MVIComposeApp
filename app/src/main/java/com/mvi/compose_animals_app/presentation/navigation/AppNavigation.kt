package com.mvi.compose_animals_app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mvi.compose_animals_app.presentation.features.animal_details.AnimalDetailsScreen
import com.mvi.compose_animals_app.presentation.features.animal_list.AnimalListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.AnimalList.route
    ) {
        composable(route = Screens.AnimalList.route) {
            AnimalListScreen(navController = navController)
        }

        composable(
            route = Screens.AnimalDetails.route,
            arguments = listOf(navArgument(AnimalDetailsArgs.NAME) {
                type = NavType.StringType
            })
        ) {
            AnimalDetailsScreen(navController = navController)
        }
    }
}

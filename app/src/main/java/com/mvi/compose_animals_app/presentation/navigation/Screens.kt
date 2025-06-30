package com.mvi.compose_animals_app.presentation.navigation

private object ScreensNames {
    const val ANIMAL_LIST_SCREEN = "AnimalListScreen"
    const val ANIMAL_DETAILS_SCREEN = "AnimalDetailsScreen"
}

object AnimalDetailsArgs {
    const val NAME = "name"
}

sealed class Screens(val route: String) {
    data object AnimalList : Screens(route = ScreensNames.ANIMAL_LIST_SCREEN)
    data object AnimalDetails :
        Screens(route = "${ScreensNames.ANIMAL_DETAILS_SCREEN}/{${AnimalDetailsArgs.NAME}}") {
        fun routeWithArgs(name: String): String = "${ScreensNames.ANIMAL_DETAILS_SCREEN}/$name"
    }
}

package com.mvi.compose_animals_app.core.extensions

import android.app.Activity
import androidx.navigation.NavController

fun NavController.popBackStackOrFinish() {
    val wasPopped = popBackStack()
    if (!wasPopped) {
        (context as? Activity)?.finish()
    }
}

fun NavController.navigateIfNotCurrent(targetRoute: String) {
    val current = currentDestination?.route?.substringBefore("/")
    val target = targetRoute.substringBefore("/")

    if (current != target) {
        navigate(targetRoute)
    }
}

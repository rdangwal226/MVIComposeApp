package com.mvi.compose_animals_app.presentation.features.animal_details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mvi.compose_animals_app.core.extensions.popBackStackOrFinish
import com.mvi.compose_animals_app.core.extensions.safeSend
import com.mvi.compose_animals_app.domain.model.AnimalDetails
import com.mvi.compose_animals_app.presentation.features.animal_details.intent.AnimalDetailsIntent
import com.mvi.compose_animals_app.presentation.features.animal_details.state.AnimalDetailsState

@Composable
fun AnimalDetailsScreen(navController: NavController) {
    val viewModel: AnimalDetailsViewModel = hiltViewModel()
    val uiState = viewModel.state.value

    // Triggered only once when the Composable is first composed
    LaunchedEffect(Unit) {
        viewModel.onInit()
    }

    AnimalDetailsScreen(
        uiState = uiState,
        onNavigateBack  = {
            navController.popBackStackOrFinish()
        },
        onRetry = {
            viewModel.uiIntentChannel.safeSend(AnimalDetailsIntent.LoadAnimalDetails)
        }
    )
}

@Composable
fun AnimalDetailsScreen(
    uiState: AnimalDetailsState,
    onNavigateBack: () -> Unit,
    onRetry: () -> Unit
) {
    AnimalDetailsContent(
        uiState = uiState,
        onNavigationBack = onNavigateBack,
        onRetry = onRetry
    )
}

// region Previews

@Preview
@Composable
private fun SuccessPreview() {
    AnimalDetailsContent(
        uiState = AnimalDetailsState.Success(AnimalDetails.mock()),
        onNavigationBack = {},
        onRetry = {}
    )
}

@Preview
@Composable
private fun LoadingPreview() {
    AnimalDetailsContent(
        uiState = AnimalDetailsState.Loading,
        onNavigationBack = {},
        onRetry = {}
    )
}

@Preview
@Composable
private fun ErrorPreview() {
    AnimalDetailsContent(
        uiState = AnimalDetailsState.Error(null),
        onNavigationBack = {},
        onRetry = {}
    )
}

// endregion Previews

package com.mvi.compose_animals_app.presentation.features.animal_details.state

import com.mvi.compose_animals_app.domain.model.AnimalDetails

sealed class AnimalDetailsState {
    data object Loading: AnimalDetailsState()
    data class Success(val animalDetails: AnimalDetails) : AnimalDetailsState()
    data class Error(val message: String?) : AnimalDetailsState()
}

package com.mvi.compose_animals_app.presentation.features.animal_list.intent

sealed class AnimalListIntent {
    data object LoadAnimals : AnimalListIntent()
}

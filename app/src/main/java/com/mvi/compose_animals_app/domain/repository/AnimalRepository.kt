package com.mvi.compose_animals_app.domain.repository

import com.mvi.compose_animals_app.domain.model.Animal
import com.mvi.compose_animals_app.domain.model.AnimalDetails

interface AnimalRepository {
    suspend fun getAnimals(): List<Animal>
    suspend fun getAnimalByName(name: String): AnimalDetails?
}

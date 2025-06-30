package com.mvi.compose_animals_app.data.repository

import com.mvi.compose_animals_app.data.mapper.toAnimalDetails
import com.mvi.compose_animals_app.data.mapper.toAnimalList
import com.mvi.compose_animals_app.data.source.remote.AnimalDataSource
import com.mvi.compose_animals_app.domain.model.Animal
import com.mvi.compose_animals_app.domain.model.AnimalDetails
import com.mvi.compose_animals_app.domain.repository.AnimalRepository
import javax.inject.Inject

class AnimalRepositoryImpl @Inject constructor(
    private val remoteDataSource: AnimalDataSource
) : AnimalRepository {
    override suspend fun getAnimals(): List<Animal> = remoteDataSource.getAnimals().toAnimalList()
    override suspend fun getAnimalByName(name: String): AnimalDetails? =
        remoteDataSource.getAnimals().find {
            it.name.equals(name, ignoreCase = true)
        }?.toAnimalDetails()
}
